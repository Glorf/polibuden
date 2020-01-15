//
// Created by mbien on 14.12.2019.
//

#include <iostream>
#include <algorithm>
#include <fstream>
#include "solution.h"
#include "helpers.h"

Solution::Solution(Instance *instance) : instance(instance), cost(0) {
    for(int i=0; i<4; i++)
        processorMoments[i] = 0;
}

void Solution::addTask(int taskId, int procId) {
    Task *task = instance->getById(taskId);

    if(processorMoments[procId] < task->getR())
        processorMoments[procId] = task->getR();

    processorMoments[procId] += task->getP();
    processors[procId].push_back(task);

    cost += std::max(0, processorMoments[procId]-task->getD());
}

void Solution::addTaskNoCalc(int taskId, int procId) {
    processors[procId].push_back(instance->getById(taskId));
}

void Solution::greedyAddTask(int taskId) {
    int best = 0;
    for(int i=0; i<4; i++) {
        if (best != i && processorMoments[i] < processorMoments[best])
            best = i;
    }

    addTask(taskId, best);
}

std::vector<Task *> Solution::getProcessor(int processorId) {
    return processors[processorId];
}

int Solution::getProcessorMoment(int processorId) {
    return processorMoments[processorId];
}

void Solution::print() {
    std::cout <<cost<<std::endl;
    for(int i=0; i<4; i++) {
        std::cout<<processors[i].size()<<std::endl;
        for(int j=0; j<processors[i].size(); j++) {
            std::cout<<processors[i][j]->getId()<<" ";
        }

        std::cout<<std::endl;
    }
}

void Solution::toFile(std::string outFile) {
    std::ofstream file(outFile);

    file << cost << std::endl;

    for(int i=0; i<4; i++) {
        for(int j=0; j<processors[i].size(); j++) {
            file << processors[i][j]->getId() << " ";
        }
        file << std::endl;
    }
}

int Solution::getCost() {
    return cost;
}

void Solution::mutate() {
    int mutationAllel1 = rand()%4;
    while(processors[mutationAllel1].empty())
        mutationAllel1 = rand()%4;

    int mutationAllel2 = rand()%4;
    while(processors[mutationAllel2].empty())
        mutationAllel2 = rand()%4;


    int mutationPosition1 = rand()%(processors[mutationAllel1].size());
    int mutationPosition2 = rand()%(processors[mutationAllel2].size());

    Task *gene1 = processors[mutationAllel1][mutationPosition1];
    Task *gene2 = processors[mutationAllel2][mutationPosition2];

    processors[mutationAllel1][mutationPosition1] = gene2;
    processors[mutationAllel2][mutationPosition2] = gene1;
}

void Solution::damageAllel() {
    int damaged = rand()%4;
    std::shuffle(processors[damaged].begin(), processors[damaged].end(), std::default_random_engine());
}

void Solution::recalculateCost() {
    for(int i=0; i<4; i++)
        processorMoments[i] = 0;
    cost = 0;

    int usedCounter = 0;
    bool used[instance->size()];
    for(int i=0; i<instance->size(); i++)
        used[i] = false;

    int processorCurrentElement[4] = {0,0,0,0};

    while(usedCounter<instance->size()) {
        bool wasSelected = false;
        int selectedProcessor = 0;
        int minTime = INT32_MAX;
        for (int i = 0; i < 4; i++) {
            if(processorCurrentElement[i]<processors[i].size() && processorMoments[i]<minTime) {
                minTime = processorMoments[i];
                selectedProcessor = i;
                wasSelected = true;
            }
        }

        if(!wasSelected) //There are missing allels! Add them at the ends
        {
            for(int i=0; i<instance->size(); i++) {
                if(!used[i]) {
                    int randomProc = rand()%4;
                    addTask(i+1, randomProc);
                }
            }

            return;
        }

        //std::cout<<usedCounter<<std::endl;

        for(; processorCurrentElement[selectedProcessor]<processors[selectedProcessor].size(); processorCurrentElement[selectedProcessor]++) {

            int element = processorCurrentElement[selectedProcessor];
            if(used[processors[selectedProcessor][element]->getId()-1]) //Element already in use, move to next one
                continue;
            else {
                Task *task = processors[selectedProcessor][element];
                used[task->getId()-1] = true;
                usedCounter++;

                processorMoments[selectedProcessor] = std::max(processorMoments[selectedProcessor], task->getR());
                processorMoments[selectedProcessor] += task->getP();
                cost += std::max(0, processorMoments[selectedProcessor] - task->getD());
                processorCurrentElement[selectedProcessor]++;
                break;
            }
        }
    }
}

void Solution::pruneDuplicated() {
    for(int i=0; i<4; i++)
        processorMoments[i] = 0;
    cost = 0;

    std::vector<Task *> newProcessors[4];

    int usedCounter = 0;
    bool used[instance->size()];
    for(int i=0; i<instance->size(); i++)
        used[i] = false;

    int processorCurrentElement[4] = {0,0,0,0};

    while(usedCounter<instance->size()) {
        bool wasSelected = false;
        int selectedProcessor = 0;
        int minTime = INT32_MAX;
        for (int i = 0; i < 4; i++) {
            if(processorCurrentElement[i]<processors[i].size() && processorMoments[i]<minTime) {
                minTime = processorMoments[i];
                selectedProcessor = i;
                wasSelected = true;
            }
        }

        if(!wasSelected) //There are missing allels! Add them at the ends
        {
            for(int i=0;i<4;i++) //ATM the old processors list is not needed anymore
                processors[i] = newProcessors[i];

            for(int i=0; i<instance->size(); i++) {
                if(!used[i]) {
                    int randomProc = rand()%4;
                    addTask(i+1, randomProc);
                }
            }

            return;
        }

        //std::cout<<usedCounter<<std::endl;

        for(; processorCurrentElement[selectedProcessor]<processors[selectedProcessor].size(); processorCurrentElement[selectedProcessor]++) {

            int element = processorCurrentElement[selectedProcessor];
            if(used[processors[selectedProcessor][element]->getId()-1]) //Element already in use, move to next one
                continue;
            else {
                Task *task = processors[selectedProcessor][element];
                used[task->getId()-1] = true;
                usedCounter++;
                processorMoments[selectedProcessor] = std::max(processorMoments[selectedProcessor], task->getR());
                processorMoments[selectedProcessor] += task->getP();
                cost += std::max(0, processorMoments[selectedProcessor] - task->getD());
                processorCurrentElement[selectedProcessor]++;
                newProcessors[selectedProcessor].push_back(task);
                break;
            }
        }
    }

    //Close peacefully
    for(int i=0;i<4;i++) //ATM the old processors list is not needed anymore
        processors[i] = newProcessors[i];
}

void Solution::insertString() {
    int mutationAllel1 = rand()%4;
    while(processors[mutationAllel1].empty())
        mutationAllel1 = rand()%4;
    int targetTask = rand()%instance->size() + 1;

    int mutationPosition1 = rand()%processors[mutationAllel1].size();

    Task *gene1 = instance->getById(targetTask);
    processors[mutationAllel1].insert(processors[mutationAllel1].begin()+mutationPosition1, gene1);
}

void Solution::allelFlip() {
    int allel1 = rand()%4;
    int allel2 = rand()%4;

    std::vector<Task*> allel1D = processors[allel1];
    std::vector<Task*> allel2D = processors[allel2];
    processors[allel1] = allel2D;
    processors[allel2] = allel1D;
}
