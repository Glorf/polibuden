//
// Created by mbien on 02.05.18.
//

#include <glm/gtc/matrix_transform.hpp>
#include <fstream>
#include <glm/gtc/type_ptr.hpp>
#include <glm/gtx/matrix_decompose.hpp>
#include "cell.h"
#include "engine.h"

Cell::Cell(glm::vec3 position, glm::vec3 rotation, float scale) {
    this->position = position;
    this->rotation = rotation;
    this->scale = scale;
}

Cell::~Cell() {
    for(Model *model: models) {
        delete(model);
    }
}

bool Cell::addModel(std::string modelId, std::string shaderName, glm::vec3 position, glm::vec3 rotation, glm::vec3 scale) {
    Model *model = new Model(modelId, Engine::get().getShaderProgram(shaderName));

    /*
     * Transformate into the cell space
     */

    model->modelMatrix = glm::translate(model->modelMatrix, this->position);

    model->modelMatrix = glm::rotate(model->modelMatrix, glm::radians(this->rotation.x), glm::vec3(1.f,0.f,0.f));
    model->modelMatrix = glm::rotate(model->modelMatrix, glm::radians(this->rotation.y), glm::vec3(0.f,1.f,0.f));
    model->modelMatrix = glm::rotate(model->modelMatrix, glm::radians(this->rotation.z), glm::vec3(0.f,0.f,1.f));

    model->modelMatrix = glm::scale(model->modelMatrix, glm::vec3(this->scale, this->scale, this->scale));

    /*
     * Make basic model transfotmations
     */

    //Translate model in scenespace
    model->modelMatrix = glm::translate(model->modelMatrix, position);

    //Rotate model in scenespace whatever direction user wants it
    model->modelMatrix = glm::rotate(model->modelMatrix, glm::radians(rotation.x), glm::vec3(1.f,0.f,0.f));
    model->modelMatrix = glm::rotate(model->modelMatrix, glm::radians(rotation.y), glm::vec3(0.f,1.f,0.f));
    model->modelMatrix = glm::rotate(model->modelMatrix, glm::radians(rotation.z), glm::vec3(0.f,0.f,1.f));

    //Scale model
    model->modelMatrix = glm::scale(model->modelMatrix, scale);

    //Apply the same to the collision model

    glm::vec3 btScale;
    glm::vec3 btTranslation;
    glm::quat btOrientation;
    glm::vec3 btSkew;
    glm::vec4 btPerspective;

    glm::decompose(model->modelMatrix, btScale, btOrientation, btTranslation, btSkew, btPerspective);

    btOrientation = glm::conjugate(btOrientation);

    model->rigidBody->getCollisionShape()->setLocalScaling(btVector3(btScale.x, btScale.y, btScale.z));
    btTransform trans;
    trans.setOrigin(btVector3(btTranslation.x, btTranslation.y, btTranslation.z));
    trans.setRotation(btQuaternion(btOrientation.x, btOrientation.y, btOrientation.z, btOrientation.w));
    model->rigidBody->setWorldTransform(trans);

    //Register collision model in physics engine
    Engine::get().getPhysics()->addRigidBody(model->rigidBody);

    //Save model
    models.push_back(model);

    return true;
}

void Cell::draw(glm::mat4 P, glm::mat4 V, glm::vec3 position, float lightPower) {
    for(Model *model: models) {
        model->draw(P, V, position, lightPower);
    }
}

bool Cell::fromFile(std::string cellName) {
    std::ifstream cellFile;
    cellFile.open("./assets/cells/"+cellName+".cell");
    std::string dummy;

    while(!cellFile.eof()) {
        dummy = "";
        cellFile >> dummy;

        if(dummy == "model") {
            std::string meshName;
            cellFile >> meshName;

            glm::vec3 vectors[2];
            for(int i=0; i<2; i++) {
                cellFile >> vectors[i].x >> vectors[i].y >> vectors[i].z;
            }

            glm::vec3 scale;
            cellFile >> scale.x >> scale.y >> scale.z;

            std::string shader;
            cellFile >> shader;

            addModel(meshName, shader, vectors[0], vectors[1], scale);
        }
        else continue;
    }
    cellFile.close();

    return true;
}