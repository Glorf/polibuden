//
// Created by mbien on 22.12.2019.
//

#include "helpers.h"

bool greedySorterR(Task *i1, Task *i2)
{
    return (i1->getR() < i2->getR());
}

bool greedySorterD(Task *i1, Task *i2)
{
    return (i1->getD() < i2->getD());
}

bool greedySorterP(Task *i1, Task *i2)
{
    return (i1->getP() < i2->getP());
}