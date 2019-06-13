//
// Created by mbien on 20.04.19.
//

#include "guide.h"
#include <iostream>
#include <thread>
#include <mpi.h>
#include <random>

#define SEA_REQUEST_MESSAGE 1
#define SEA_RELEASE_MESSAGE 2
#define VEHICLE_REQUEST_MESSAGE 3
#define VEHICLE_RELEASE_MESSAGE 4
#define TECHNICIAN_REQUEST_MESSAGE 5
#define TECHNICIAN_RELEASE_MESSAGE 6
#define ACK 7

Guide::Guide(int p, int m, int s, int t) : clock(0), P(p), M(m), S(s), T(t), running(true) {
    size = MPI::COMM_WORLD.Get_size();
    rank = MPI::COMM_WORLD.Get_rank();
    ack_mutex.lock(); //used to idle wait for events or ack
    wait_for_ack = -1; //clock of request which ack we wait for
    ack_counter = 0; //number of acks we got already
    srand(time(nullptr)+rank); //start with other seed for each process

    Pqueue = new s_vehicle[P];
    for(int i=0; i<P; i++) {
        Pqueue[i].status = S;
    }
}

void Guide::log(const char message[]) {
    printf("[Rank: %d|Clock: %d]: %s\n", rank, clock, message);
}

void Guide::log(std::string message) {
    printf("[Rank: %d|Clock: %d]: %s\n", rank, clock, message.c_str());
}

void Guide::createMonitorThread() {
    new std::thread(&Guide::monitorThread, this);
}


void Guide::monitorThread() {
    while(running) {
        s_request result;
        MPI_Status status;
        MPI_Recv(&result, sizeof(s_request), MPI_BYTE, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);

        clock_mutex.lock();
        clock = std::max(clock, result.clock) + 1;
        clock_mutex.unlock();

        switch(status.MPI_TAG) {
            case SEA_REQUEST_MESSAGE:
                insertToLamportVector(&Mqueue, &result);
                sendAck(result.sender_id);
                break;
            case SEA_RELEASE_MESSAGE:
                removeFromLamportVector(&Mqueue, result.sender_id);
                Mqueue.event_mutex.unlock();
                break;
            case VEHICLE_REQUEST_MESSAGE:
                insertToLamportVector(&Pqueue[result.value].queue, &result);
                sendAck(result.sender_id);
                break;
            case VEHICLE_RELEASE_MESSAGE:
                removeFromLamportVector(&Pqueue[result.value].queue, result.sender_id);
                Pqueue[result.value].status = result.value2;
                Pqueue[result.value].queue.event_mutex.unlock();
                break;
            case TECHNICIAN_REQUEST_MESSAGE:
                insertToLamportVector(&Tqueue, &result);
                sendAck(result.sender_id);
                break;
            case TECHNICIAN_RELEASE_MESSAGE:
                removeFromLamportVector(&Tqueue, result.sender_id);
                Tqueue.event_mutex.unlock();
                break;
            case ACK:
                if(wait_for_ack!=-1 && result.clock>wait_for_ack) {
                    ack_counter++;
                    if(ack_counter==size-1) {
                        ack_counter = 0;
                        wait_for_ack = -1;
                        ack_mutex.unlock();
                    }
                }
                break;
        }
    }
}

void Guide::sendAck(int receiver) {
    s_request ack = create_request(0);

    MPI_Send(&ack, sizeof(s_request), MPI_BYTE, receiver, ACK, MPI_COMM_WORLD);
}

s_request Guide::create_request(int value) {
    s_request request;
    request.value = value;
    clock_mutex.lock();
    request.clock = clock;
    clock++;
    clock_mutex.unlock();
    request.sender_id = rank;
    return request;
}

void Guide::runPerformThread() {
    while(running) {
        //Spontanously spawn passengers
        std::this_thread::sleep_for(std::chrono::milliseconds{rand()%10000 + 1000}); //sleep for 1-10 sec

        int x = (rand()%M)+1;

        log("Sending request of sea for "+std::to_string(x)+" passengers");
        //Prepare request with incremented clk
        s_request sea_request = create_request(x);
        //Modify queue
        insertToLamportVector(&Mqueue, &sea_request);
        //Send sea request and wait for acks
        wait_for_ack = sea_request.clock;
        broadcastRequest(&sea_request, SEA_REQUEST_MESSAGE);
        ack_mutex.lock();

        //Try to allocate sea
        while(true) {
            Mqueue.event_mutex.lock();
            Mqueue.edit_mutex.lock();
            int sum = 0;
            for(auto it = Mqueue.vector.begin(); it->sender_id!=rank; it++) {
                sum += it->value;
            }
            Mqueue.edit_mutex.unlock();

            if(sum + x <= M) {
                log("Successfully allocated sea. The state of sea is now "+std::to_string(sum+x)+"/"+std::to_string(M)+" passengers!");
                Mqueue.event_mutex.unlock();
                break;
            }
        }

        //Select vehicle
        //Dice roll between vehicles that were least used
        int choice = get_least_used_vehicle();
        s_request vehicle_request = create_request(choice);
        s_lamport_vector *vehicle = &Pqueue[choice].queue;

        log("Trying to allocate vehicle "+ std::to_string(choice));
        insertToLamportVector(vehicle, &vehicle_request);
        wait_for_ack = vehicle_request.clock;
        broadcastRequest(&vehicle_request, VEHICLE_REQUEST_MESSAGE);
        ack_mutex.lock();

        //Try to allocate vehicle
        while(true) {
            vehicle->event_mutex.lock();
            if(vehicle->vector[0].sender_id == rank) {
                log("Successfully allocated vehicle nr "+ std::to_string(choice) + " in state " + std::to_string(Pqueue[choice].status) + "! Now sailing...");
                vehicle->event_mutex.unlock();
                break;
            }
        }

        //The guide pretends to be sailing now
        std::this_thread::sleep_for(std::chrono::milliseconds{rand()%10000 + 1000}); //sleep for 1-10 sec

        //Deallocate sea
        removeFromLamportVector(&Mqueue, rank);
        s_request dealloc_sea = create_request(x);
        broadcastRequest(&dealloc_sea, SEA_RELEASE_MESSAGE);
        log("Released sea");


        int vstatus = Pqueue[choice].status - 1;
        if(vstatus == 0) {
            log("Trying to allocate technician");
            s_request tech_request = create_request(1);
            insertToLamportVector(&Tqueue, &tech_request);
            wait_for_ack = tech_request.clock;
            broadcastRequest(&tech_request, TECHNICIAN_REQUEST_MESSAGE);
            ack_mutex.lock();

            while(true) {
                Tqueue.event_mutex.lock();
                Tqueue.edit_mutex.lock();
                int sum = 0;
                for(auto it = Tqueue.vector.begin(); it->sender_id!=rank; it++) {
                    sum += it->value;
                }
                Tqueue.edit_mutex.unlock();

                if(sum<T) {
                    log("Successfully allocated technician. Repairing vehicle " + std::to_string(choice) + "...");
                    Tqueue.event_mutex.unlock();
                    break;
                }

            }

            std::this_thread::sleep_for(std::chrono::milliseconds{rand()%10000 + 1000}); //sleep for 1-10 sec
            vstatus = S;

            removeFromLamportVector(&Tqueue, rank);
            s_request dealloc_tech = create_request(0);
            broadcastRequest(&dealloc_tech, TECHNICIAN_RELEASE_MESSAGE);
            log("Released technician. The vehicle is now repared");
        }

        removeFromLamportVector(&Pqueue[choice].queue, rank);
        s_request dealloc_vec = create_request(choice);
        dealloc_vec.value2 = vstatus; //TODO: find more efficient way to do this
        broadcastRequest(&dealloc_vec, VEHICLE_RELEASE_MESSAGE);
        log("Released vehicle " + std::to_string(choice) + " in state " + std::to_string(vstatus));
    }
}

int Guide::get_least_used_vehicle() {
    std::vector<int> best;
    int best_value = size; //global max
    for(int i = 0; i < P; i++){
        Pqueue[i].queue.edit_mutex.lock();
        int vec_siz = Pqueue[i].queue.vector.size();
        if(best_value > vec_siz) {
            best_value = vec_siz;
            best.clear();
            best.push_back(i);
        }
        else if(best_value == vec_siz) {
            best.push_back(i);
        }
        Pqueue[i].queue.edit_mutex.unlock();
    }
    return best[rand()%best.size()];
}

void Guide::insertToLamportVector(s_lamport_vector *vector, s_request *request) {
    //Modify queue
    vector->edit_mutex.lock();
    std::vector<s_request>::iterator it;
    for(it = vector->vector.begin(); it<vector->vector.end(); it++) {
        if(it->clock > request->clock || (it->clock == request->clock && it->sender_id > request->sender_id))
            break;
    }
    vector->vector.insert(it, *request);
    vector->edit_mutex.unlock();
}

void Guide::removeFromLamportVector(s_lamport_vector *vector, int sender) {
    vector->edit_mutex.lock();
    std::vector<s_request>::iterator it;
    for(it = vector->vector.begin(); it<vector->vector.end(); it++) {
        if(it->sender_id == sender) {
            vector->vector.erase(it);
            break;
        }
    }
    vector->edit_mutex.unlock();
}

void Guide::broadcastRequest(s_request *request, int request_type) {
    for(int i=0; i<size; i++)
        if(i!=rank)
            MPI_Send(request, sizeof(s_request), MPI_BYTE, i, request_type, MPI_COMM_WORLD);
}
