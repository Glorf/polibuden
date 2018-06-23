//
// Created by mbien on 02.05.18.
//

#ifndef PUTGEON_MAP_H
#define PUTGEON_MAP_H


#include "cell.h"

class Map {
public:
    std::vector<Cell*> cells;
    Map();
    ~Map();
    bool fromFile(std::string fileName);
};


#endif //PUTGEON_MAP_H
