//
// Created by mbien on 14.12.2019.
//

#ifndef GENETYK_TASK_H
#define GENETYK_TASK_H


class Task {
public:
    Task(int id, int r, int d, int p);
    int getR();
    int getD();
    int getP();
    int getId();
private:
    int id;
    int r;
    int d;
    int p;
};


#endif //GENETYK_TASK_H
