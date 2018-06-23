//
// Created by mbien on 02.05.18.
//

#ifndef PUTGEON_CELL_H
#define PUTGEON_CELL_H


#include <vector>
#include "model.h"

class Cell {
    std::vector<Model*> models;

    glm::vec3 position;
    glm::vec3 rotation;
    float scale;

public:
    Cell(glm::vec3 position, glm::vec3 rotation, float scale);
    ~Cell();
    bool addModel(std::string modelId, std::string shaderName, glm::vec3 position, glm::vec3 rotation, glm::vec3 scale);
    bool fromFile(std::string cellName);
    void draw(glm::mat4 P, glm::mat4 V, glm::vec3 position, float lightPower);
};


#endif //PUTGEON_CELL_H
