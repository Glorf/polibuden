//
// Created by mbien on 14.12.2019.
//

#ifndef GENETYK_INSTANCE_H
#define GENETYK_INSTANCE_H

#include "task.h"
#include <vector>
#include <string>

class Instance {
public:
    explicit Instance(const std::string &filename);
    Task *getById(int id);
    int size();
    void print();
private:
    std::vector<Task> taskList;
    int n;
};


#endif //GENETYK_INSTANCE_H
