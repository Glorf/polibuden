//
// Created by mbien on 02.04.18.
//

#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <fstream>
#include <sstream>
#include <vector>
#include <glm/gtc/constants.hpp>
#include <glm/gtc/type_ptr.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/glm.hpp>
#include <cmath>
#include <random>
#include "mesh.h"
#include "engine.h"
#include "model.h"

bool Engine::init() {
    if(!glfwInit()) {
        std::cerr << "Failed to initialize GLFW" << std::endl;
        return false;
    }

    glfwWindowHint(GLFW_SAMPLES, 4);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    return true;
}

Engine::Engine() {
    working = true;
    physics = new Physics();
    actor = new Actor();
    physics->addRigidBody(actor->bouncingBox);
}

void Engine::destruct() {
    glfwTerminate();
    delete(map);
    delete(actor);

    for(auto it = materials.begin(); it!=materials.end(); ++it) {
        delete((*it).second);
    }

    for(auto it = shaderPrograms.begin(); it!=shaderPrograms.end(); ++it){
        delete((*it).second);
    }
}

bool Engine::setWindow(int w, int h, std::string name) {
    window = glfwCreateWindow(w, h, name.c_str(), nullptr, nullptr);
    if(window == nullptr){
        std::cerr << "Failed to open GLFW window." << std::endl;
        glfwTerminate();
        return false;
    }
    glfwMakeContextCurrent(window);

    if (glewInit() != GLEW_OK) {
        std::cerr << "Failed to initialize GLEW" << std::endl;
        glfwTerminate();
        return false;
    }

    glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
    glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LESS);
    glEnable(GL_CULL_FACE);

    width = w;
    height = h;

    return true;
}


void Engine::clearToColor(float r, float g, float b, float alpha) {
    glClearColor(r, g, b, alpha);
}

bool Engine::addShaderProgram(std::string programName, std::string vertexFile, std::string fragmentFile) {
    auto *program = new ShaderProgram(vertexFile, fragmentFile);
    if(!program->isReady()) {
        std::cerr << "Loading shader program " << programName << " failed!"<<std::endl;
        return false;
    }

    shaderPrograms.emplace(programName, program);

    return true;
}

ShaderProgram* Engine::getShaderProgram(std::string programName){
    return (*shaderPrograms.find(programName)).second;
}

bool Engine::loadMap(std::string mapName) {
    map = new Map();
    return map->fromFile(mapName);
}

Physics* Engine::getPhysics() {
    return physics;
}

void Engine::run() {
    glfwSetCursorPos(window, width/2, height/2);
    glm::mat4 P = glm::perspective(glm::radians(45.0f), 4.0f / 3.0f, 0.001f, 10.0f);

    glm::mat4 V = actor->getVMatrix();
    glm::vec3 position = actor->getPosition();

    while(working) {
        working = glfwWindowShouldClose(window)==0;

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        double xpos, ypos;
        glfwGetCursorPos(window, &xpos, &ypos);
        glfwSetCursorPos(window, width/2, height/2);


        // Compute new orientation
        actor->rotate(mouseSpeed * float(width/2 - xpos), mouseSpeed * float(height/2 - ypos));


        //Key events TODO: add can move
        if(glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) actor->move(glm::vec3(1,0,0));
        if(glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) actor->move(glm::vec3(-1,0,0));
        if(glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) actor->move(glm::vec3(0,0,1));
        if(glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) actor->move(glm::vec3(0,0,-1));
        if(glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS) actor->jump();
        if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) working = false;


        //Draw objects
        V = actor->getVMatrix();
        position = actor->getPosition();


        float lightPower = actor->getLightPower();
        for(Cell *cell: map->cells) {

            cell->draw(P, V, position, lightPower);
        }

        actor->update(); //Do a world tick - update stuff

        glfwSwapBuffers(window);
        glfwPollEvents();

    }
}

void Engine::setMouseSpeed(float speed) {
    this->mouseSpeed = speed;
}

Material* Engine::getMaterial(std::string name) {
    return (*materials.find(name)).second;
}

void Engine::addMaterial(Material *material) {
    materials.emplace(material->name, material);
}

bool Engine::isMaterialLoaded(std::string name) {
    return materials.find(name)!=materials.end();
}