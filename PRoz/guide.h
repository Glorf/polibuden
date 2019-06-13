//
// Created by mbien on 20.04.19.
//

#ifndef IMPOSSIBILITY_GUIDE_H
#define IMPOSSIBILITY_GUIDE_H
#include <string>
#include <vector>
#include <mutex>
#include <atomic>

typedef struct {
    int value;
    int value2; //For vehicle release status only
    int sender_id;
    int clock;
} s_request;

typedef struct {
    std::mutex edit_mutex;
    std::mutex event_mutex;
    std::vector<s_request> vector;
} s_lamport_vector;


typedef struct {
    int status;
    s_lamport_vector queue;
} s_vehicle;

class Guide {
    int size;
    int rank;
    int clock;
    std::mutex clock_mutex;

    std::mutex ack_mutex;
    int wait_for_ack;
    int ack_counter;

    int P;
    int M;
    int S;
    int T;
    s_lamport_vector Mqueue;
    s_vehicle *Pqueue;
    s_lamport_vector Tqueue;

    bool running;
    void log(const char[]);
    void log(std::string);

    void monitorThread();
    void insertToLamportVector(s_lamport_vector *vector, s_request *request);
    void removeFromLamportVector(s_lamport_vector *vector, int sender);
    void broadcastRequest(s_request *request, int request_type);
    void sendAck(int receiver);
    s_request create_request(int value);
    int get_least_used_vehicle();

public:
    Guide(int p, int m, int s, int t);
    void createMonitorThread();
    void runPerformThread();
};


#endif //IMPOSSIBILITY_GUIDE_H
