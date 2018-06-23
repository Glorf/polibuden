//
// Created by mbien on 02.04.18.
//

#include <fstream>
#include <GL/glew.h>
#include <glm/gtc/type_ptr.hpp>
#include <BulletDynamics/Dynamics/btRigidBody.h>
#include <LinearMath/btDefaultMotionState.h>
#include "model.h"
#include "engine.h"

void Model::assignVBOtoAttribute(const std::string &attributeName, GLuint bufVBO, int vertexSize) {
    GLuint location=shaderProgram->getAttribLocation(attributeName);
    glBindBuffer(GL_ARRAY_BUFFER, bufVBO);
    glEnableVertexAttribArray(location);
    glVertexAttribPointer(location,vertexSize,GL_FLOAT, GL_FALSE, 0, nullptr);
}

Model::Model(std::string meshName, ShaderProgram *program) {
    mesh = new Mesh();
    mesh->loadFile("./assets/meshes/"+meshName+".obj");

    if(!parseMaterial("./assets/meshes/"+meshName+".mtl")) {
        std::cerr << "Failed to load material file: " + meshName << std::endl;
    }

    shaderProgram = program;

    //Generuj VAO
    glGenVertexArrays(1, &vao);
    glBindVertexArray(vao);

    assignVBOtoAttribute("vVertexMS",mesh->vertexBuffer, 3);
    assignVBOtoAttribute("vTexCoords",mesh->texCoordsBuffer, 2);
    assignVBOtoAttribute("vNormalsMS", mesh->normalsBuffer, 3);
    assignVBOtoAttribute("vTangentsMS", mesh->tangentBuffer, 3);
    assignVBOtoAttribute("vBitangentsMS", mesh->bitangentBuffer, 3);

    glBindVertexArray(0);

    modelMatrix = glm::mat4(1.0f);

    btDefaultMotionState* motionstate = new btDefaultMotionState();

    btRigidBody::btRigidBodyConstructionInfo rigidBodyCI(
            0,                  // mass, in kg. 0 -> Static object, will never move.
            motionstate,        // initial position and orientation
            mesh->collisionShape,  // collision shape of body
            btVector3(0,0,0)    // local inertia
    );
    rigidBody = new btRigidBody(rigidBodyCI);
    rigidBody->setUserPointer(this);
    rigidBody->setRestitution(0);
}

Model::~Model() {
    delete(mesh);

    glDeleteVertexArrays(1,&vao);
}

bool Model::parseMaterial(const std::string &materialPath) {
    std::ifstream mtlFile;
    mtlFile.open(materialPath);
    std::string dummy;

    while(!mtlFile.eof()) {
        mtlFile >> dummy;

        if(dummy == "newmtl") {
            std::string mtlname;

            mtlFile >> mtlname;
            if(Engine::get().isMaterialLoaded(mtlname)) {
                material = Engine::get().getMaterial(mtlname);
                mtlFile.close();
                return true;
            }
            else {
                mtlFile.close();
                material = new Material();
                if(material->loadMtl(materialPath)) {
                    Engine::get().addMaterial(material);
                    return true;
                }

                return false;
            }
        }
    }

    return false;
}

void Model::draw(glm::mat4 P, glm::mat4 V, glm::vec3 position, float lightPower) {
    this->shaderProgram->use();

    //Set uniforms
    glUniformMatrix4fv(this->shaderProgram->getUniformLocation("uP"), 1, GL_FALSE, glm::value_ptr(P));
    glUniformMatrix4fv(this->shaderProgram->getUniformLocation("uV"), 1, GL_FALSE, glm::value_ptr(V));
    glUniformMatrix4fv(this->shaderProgram->getUniformLocation("uM"), 1, GL_FALSE, glm::value_ptr(this->modelMatrix));
    glUniform3fv(this->shaderProgram->getUniformLocation("uPosition"), 1, glm::value_ptr(position));
    glUniform1i(this->shaderProgram->getUniformLocation("uTextureMap0"), 0);
    glUniform1i(this->shaderProgram->getUniformLocation("uTextureMap1"), 1);
    glUniform1i(this->shaderProgram->getUniformLocation("uTextureMap2"), 2);
    glUniform1f(this->shaderProgram->getUniformLocation("uLightPower"), lightPower);

    //load diffusion map to sampler0
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, this->material->texture->getId());

    //load normal map to sampler1
    glActiveTexture(GL_TEXTURE1);
    glBindTexture(GL_TEXTURE_2D, this->material->normalMap->getId());

    //load specular color map to sampler2
    glActiveTexture(GL_TEXTURE2);
    glBindTexture(GL_TEXTURE_2D, this->material->lightMap->getId());

    //Draw object
    glBindVertexArray(this->vao);
    glDrawArrays(GL_TRIANGLES, 0, this->mesh->getSize());
    glBindVertexArray(0);
}