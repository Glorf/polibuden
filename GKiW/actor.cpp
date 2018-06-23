//
// Created by mbien on 06.05.18.
//

#include <glm/gtc/constants.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <GLFW/glfw3.h>
#include <LinearMath/btDefaultMotionState.h>
#include <BulletCollision/CollisionShapes/btBoxShape.h>
#include <glm/gtc/type_ptr.hpp>
#include <iostream>
#include "actor.h"
#include "engine.h"

Actor::Actor() {
    horizontalAngle = glm::half_pi<float>();
    verticalAngle = 0;
    speed = 5;
    torchCounter = 0;
    lightPower = 1.f;
    inJump = false;
    btCollisionShape* boxCollisionShape = new btBoxShape(btVector3(0.2, 0.2, 0.2));
    btVector3 inertia(0,0,0);
    boxCollisionShape->calculateLocalInertia(70, inertia);

    btDefaultMotionState* motionstate = new btDefaultMotionState();
    btTransform initalTransform;
    btVector3 initialPos(0.f, 0.5f, 0.2f);
    initalTransform.setOrigin(initialPos);
    btQuaternion initialRot(0,0,0,1);
    initalTransform.setRotation(initialRot);
    motionstate->setWorldTransform(initalTransform);

    btRigidBody::btRigidBodyConstructionInfo rigidBodyCI(
            70,                  // mass, in kg
            motionstate,        // initial position and orientation
            boxCollisionShape,  // collision shape of body
            btVector3(0,0,0)    // local inertia
    );
    bouncingBox = new btRigidBody(rigidBodyCI);
    bouncingBox->setRestitution(0);
}

void Actor::move(glm::vec3 offset) {
    glm::vec3 velocity(bouncingBox->getLinearVelocity().x(),
                        bouncingBox->getLinearVelocity().y(),
                        bouncingBox->getLinearVelocity().z());
    velocity += offset.x * direction * timeDelta * speed;
    velocity += offset.z * right * timeDelta * speed;

    btVector3 btVelocity(velocity.x, velocity.y, velocity.z);
    bouncingBox->setLinearVelocity(btVelocity);
}

void Actor::rotate(float xRotation, float yRotation) {
    horizontalAngle += xRotation;
    verticalAngle   += yRotation;
}

std::default_random_engine generator;
std::normal_distribution<double> dist(-0.1f, 0.1f);

void Actor::update() {
    double currentTime = glfwGetTime();
    if(time == 0) time = currentTime;
    timeDelta = float(currentTime - time);
    time = currentTime;

    torchCounter++;

    if(torchCounter==5) {
        lightPower += dist(generator);
        lightPower = std::max(lightPower, 0.5f);
        lightPower = std::min(lightPower, 1.8f);
        torchCounter = 0;
    }

    if(std::abs(bouncingBox->getLinearVelocity().y())<0.1)
        inJump = false;

    // Block max angles
    verticalAngle = std::max(-glm::half_pi<double>(), verticalAngle);
    verticalAngle = std::min(glm::half_pi<double>(), verticalAngle);

    Engine::get().getPhysics()->stepSimulation(timeDelta);
    bouncingBox->activate(true);
}

void Actor::jump() {
    if(inJump)
        return; //already in air
    btVector3 jump(0, 2, 0);
    bouncingBox->setLinearVelocity(bouncingBox->getLinearVelocity()+jump);
    inJump = true;
}

glm::vec3 Actor::getPosition() {
    return glm::vec3(bouncingBox->getCenterOfMassPosition().x(),
                       bouncingBox->getCenterOfMassPosition().y(),
                       bouncingBox->getCenterOfMassPosition().z());
}

glm::mat4 Actor::getVMatrix() {
    // Direction : Spherical coordinates to Cartesian coordinates conversion
    direction = glm::vec3(
            cos(verticalAngle) * sin(horizontalAngle),
            sin(verticalAngle),
            cos(verticalAngle) * cos(horizontalAngle)
    );

    // Right vector
    right = glm::vec3(
            sin(horizontalAngle - glm::half_pi<double>()),
            0,
            cos(horizontalAngle - glm::half_pi<double>())
    );

    glm::vec3 up = glm::cross(right, direction);

    glm::mat4 V = glm::lookAt(getPosition(), getPosition()+direction, up);
    return V;
}

float Actor::getLightPower() {
    return lightPower;
}