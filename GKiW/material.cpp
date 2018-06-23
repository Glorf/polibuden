//
// Created by mbien on 03.04.18.
//

#include <fstream>
#include "material.h"

Material::Material() {
    emmisive = false;
}

Material::~Material() {
    delete(texture);
    delete(normalMap);
    delete(lightMap);
    if(emmisive)
        delete(emmisionMap);
}

bool Material::loadMtl(std::string filePath) {
    std::ifstream mtlFile;
    mtlFile.open(filePath);
    std::string dummy;

    while(!mtlFile.eof()) {
        dummy = "";
        mtlFile >> dummy;

        if(dummy == "newmtl") {
            mtlFile >> name;
        }
        else if(dummy == "map_Kd") {
            std::string textureName;
            mtlFile >> textureName;
            std::cout << textureName << std::endl;

            texture = new Texture();
            texture->loadPNG("./assets/textures/"+textureName);
        }
        else if(dummy == "map_Bump") {
            std::string normapName;
            mtlFile >> normapName;
            std::cout << normapName << std::endl;

            normalMap = new Texture();
            normalMap->loadPNG("./assets/textures/"+normapName);
        }
        else if(dummy == "map_Ks") {
            std::string lightmapName;
            mtlFile >> lightmapName;
            std::cout << lightmapName << std::endl;

            lightMap = new Texture();
            lightMap->loadPNG("./assets/textures/"+lightmapName);
        }

    }

    return true;
}