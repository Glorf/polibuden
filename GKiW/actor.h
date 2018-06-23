//
// Created by mbien on 06.05.18.
//

#ifndef PUTGEON_ACTOR_H
#define PUTGEON_ACTOR_H

#include <glm/vec3.hpp>
#include <glm/detail/type_mat4x4.hpp>
#include <random>
#include <BulletDynamics/Dynamics/btRigidBody.h>

class Actor {
    double horizontalAngle;
    double verticalAngle;
    glm::vec3 direction;
    glm::vec3 right;
    float speed;
    float lightPower;
    double time;
    float timeDelta;
    int torchCounter;
    bool inJump;

public:
    Actor();
    void move(glm::vec3 offset); //movement
    void rotate(float xRotation, float yRotation);

    void jump(); //Trigger jump
    void update(); //Gravitation, mouse, torch, jump acceleration etc

    glm::vec3 getPosition(); //To shader
    glm::mat4 getVMatrix(); //To draw
    float getLightPower();
    bool canMove(glm::vec3 newPosition); //Collision detection
    btRigidBody *bouncingBox;
};


#endif //PUTGEON_ACTOR_H
