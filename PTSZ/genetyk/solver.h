//
// Created by mbien on 14.12.2019.
//

#ifndef GENETYK_SOLVER_H
#define GENETYK_SOLVER_H

#include "instance.h"
#include "solution.h"

class Solver {
public:
    explicit Solver(Instance *instance);
    Solution solve();
    Solution generateSimpleR();
    Solution generateSimpleD();
    Solution generateSimpleP();
    Solution generateWoodpecker();
    Solution generateRandom();
    void mutate(Solution *solution);
    void damageAllel(Solution *solution);
    void allelFlip(Solution *solution);
    void insertString(Solution *solution);

private:
    Instance *instance;
    std::random_device rd;
};

#endif //GENETYK_SOLVER_H
