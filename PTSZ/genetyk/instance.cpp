//
// Created by mbien on 14.12.2019.
//

#include "instance.h"
#include "task.h"

#include <fstream>
#include <sstream>
#include <iostream>

Instance::Instance(const std::string &filename) {
    std::ifstream file(filename);

    std::string line;
    bool header = true;
    int id = 1;
    while(std::getline(file, line)) {
        std::istringstream iss(line);
        if(header) {
            iss >> n;
            //std::cout<<n<<std::endl;
            header = false;
        }
        else {
            int p,r,d;
            iss >> p >> r >> d;
            Task newTask(id, r, d, p);
            taskList.push_back(newTask);
            id++;
        }
    }
}

Task * Instance::getById(int id) {
    return &taskList.at(id-1);
}

int Instance::size() {
    return n;
}

void Instance::print() {
    for(int i=1;i<=size(); i++) {
        std::cout<<getById(i)->getD()<< " " << getById(i)->getP() << " " <<getById(i)->getR() <<std::endl;
    }
}