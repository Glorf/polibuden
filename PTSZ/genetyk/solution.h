//
// Created by mbien on 14.12.2019.
//

#ifndef GENETYK_SOLUTION_H
#define GENETYK_SOLUTION_H


#include "task.h"
#include "instance.h"
#include <vector>
#include <random>

class Solution {
public:
    Solution(Instance *instance);
    void addTask(int taskId, int procId);
    void addTaskNoCalc(int taskId, int procId);
    void greedyAddTask(int taskId);
    std::vector<Task*> getProcessor(int processorId);
    int getProcessorMoment(int processorId);
    int getCost();
    void print();
    void toFile(std::string outFile);
    void mutate();
    void insertString();
    void allelFlip();
    void damageAllel();
    void recalculateCost();
    void pruneDuplicated();
private:
    Instance *instance;
    std::vector<Task*> processors[4];
    int processorMoments[4];
    int cost;
};


#endif //GENETYK_SOLUTION_H
