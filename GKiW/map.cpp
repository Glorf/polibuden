//
// Created by mbien on 02.05.18.
//

#include <fstream>
#include "map.h"

Map::Map() {

}

Map::~Map() {
    for(Cell *cell: cells)
        delete(cell);
}

bool Map::fromFile(std::string fileName) {
    std::ifstream mapFile;
    mapFile.open("./assets/maps/"+fileName+".map");
    std::string dummy;

    while(!mapFile.eof()) {
        dummy = "";
        mapFile >> dummy;

        if(dummy == "cell") {
            std::string cellFile;
            mapFile >> cellFile;
            std::cout<<cellFile<<std::endl;

            glm::vec3 vectors[2];
            for(int i=0; i<2; i++) {
                mapFile >> vectors[i].x >> vectors[i].y >> vectors[i].z;
            }

            float scale;
            mapFile >> scale;

            Cell *cell = new Cell(vectors[0], vectors[1], scale);
            cell->fromFile(cellFile);
            cells.push_back(cell);
        }
    }

    mapFile.close();

    return true;
}