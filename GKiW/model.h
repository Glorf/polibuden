//
// Created by mbien on 02.04.18.
//

#ifndef PUTGEON_MODEL_H
#define PUTGEON_MODEL_H

#include "material.h"
#include "mesh.h"
#include "shaderprogram.h"
#include <BulletDynamics/Dynamics/btRigidBody.h>

class Model {
    void assignVBOtoAttribute(const std::string &attributeName, GLuint bufVBO, int vertexSize);
protected:
    bool parseMaterial(const std::string &materialPath);
public:
    Mesh *mesh;
    Material *material;
    ShaderProgram *shaderProgram;
    glm::mat4 modelMatrix;
    GLuint vao;
    btRigidBody *rigidBody;

    void draw(glm::mat4 P, glm::mat4 V, glm::vec3 position, float lightPower);

    Model(std::string meshName, ShaderProgram *shaderProgram);
    ~Model();
};


#endif //PUTGEON_MODEL_H
