//
// Created by mbien on 14.12.2019.
//

#include "task.h"

Task::Task(int id, int r, int d, int p) : id(id), r(r), d(d), p(p) {}

int Task::getD() {return d;}
int Task::getP() {return p;}
int Task::getR() {return r;}
int Task::getId() {return id;}