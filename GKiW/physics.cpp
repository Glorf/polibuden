//
// Created by mbien on 17.05.18.
//

#include <BulletCollision/BroadphaseCollision/btDbvtBroadphase.h>
#include <bullet/BulletCollision/Gimpact/btGImpactCollisionAlgorithm.h>
#include "physics.h"

Physics::Physics() {
    broadphase = new btDbvtBroadphase();
    collisionConfiguration = new btDefaultCollisionConfiguration();
    dispatcher = new btCollisionDispatcher(collisionConfiguration);
    btGImpactCollisionAlgorithm::registerAlgorithm(dispatcher);
    solver = new btSequentialImpulseConstraintSolver;
    dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
    dynamicsWorld->setGravity(btVector3(0, -9.81f, 0));
}

Physics::~Physics() {
    delete(dynamicsWorld);
    delete(solver);
    delete(dispatcher);
    delete(collisionConfiguration);
    delete(broadphase);
}

void Physics::addRigidBody(btRigidBody *rigidBody) {
    dynamicsWorld->addRigidBody(rigidBody);
}

void Physics::stepSimulation(float timeStep) {
    dynamicsWorld->stepSimulation(timeStep);
    dynamicsWorld->debugDrawWorld();
}