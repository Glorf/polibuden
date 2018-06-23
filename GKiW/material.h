//
// Created by mbien on 03.04.18.
//

#ifndef PUTGEON_MATERIAL_H
#define PUTGEON_MATERIAL_H

#include <glm/glm.hpp>
#include "texture.h"

class Material {
public:
    Material();
    ~Material();
    bool loadMtl(std::string filePath);
    bool emmisive;

    float specularExponent;

    glm::vec3 ambientColor;
    glm::vec3 diffuseColor;
    glm::vec3 specularColor;

    float transparency;
    int illumunationMode;

    std::string name;
    Texture *texture;
    Texture *normalMap;
    Texture *lightMap;
    Texture *emmisionMap;
};


#endif //PUTGEON_MATERIAL_H
