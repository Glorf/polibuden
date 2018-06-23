//
// Created by mbien on 17.05.18.
//

#ifndef PUTGEON_PHYSICS_H
#define PUTGEON_PHYSICS_H

#include <BulletCollision/BroadphaseCollision/btBroadphaseInterface.h>
#include <BulletCollision/CollisionDispatch/btDefaultCollisionConfiguration.h>
#include <BulletCollision/CollisionDispatch/btCollisionDispatcher.h>
#include <BulletDynamics/ConstraintSolver/btSequentialImpulseConstraintSolver.h>
#include <BulletDynamics/Dynamics/btDiscreteDynamicsWorld.h>
#include "model.h"

class Physics {
    btBroadphaseInterface *broadphase;
    btDefaultCollisionConfiguration *collisionConfiguration;
    btCollisionDispatcher *dispatcher;
    btSequentialImpulseConstraintSolver *solver;

public:
    Physics();
    ~Physics();
    void addRigidBody(btRigidBody *rigidBody);
    btDiscreteDynamicsWorld *dynamicsWorld;
    void stepSimulation(float timeStep);
};


#endif //PUTGEON_PHYSICS_H
