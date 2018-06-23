//
// Created by mbien on 02.04.18.
//

#ifndef PUTGEON_ENGINE_H
#define PUTGEON_ENGINE_H

#include <iostream>
#include <glm/glm.hpp>
#include <vector>
#include <map>
#include "shaderprogram.h"
#include "cell.h"
#include "map.h"
#include "actor.h"
#include "physics.h"

typedef unsigned int GLuint;
struct GLFWwindow;
struct Model;
struct Material;

class Engine {
    //WINDOW
    GLFWwindow *window;
    float width;
    float height;

    Map *map;
    Physics *physics;

    std::map<std::string, Material*> materials;
    std::map<std::string, Mesh*> meshes; //TODO: zrobić jednorazowe wczytywanie meshy
    std::map<std::string, ShaderProgram*> shaderPrograms;
    bool working;


    //PLAYER
    Actor *actor;
    float mouseSpeed;

public:
    static Engine &get() {
        static Engine instance;
        return instance;
    }

    Engine();
    void destruct();

    bool init();
    bool setWindow(int width, int height, std::string name);
    void clearToColor(float r, float g, float b, float alpha);
    bool addShaderProgram(std::string programName, std::string vertexFile, std::string fragmentFile);
    ShaderProgram *getShaderProgram(std::string programName);
    bool loadMap(std::string mapName);

    void setMouseSpeed(float speed);

    Physics *getPhysics();

    void run();

    Material *getMaterial(std::string name);
    void addMaterial(Material *material);
    bool isMaterialLoaded(std::string name);
};


#endif //PUTGEON_ENGINE_H
