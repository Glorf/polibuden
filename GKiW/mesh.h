//
// Created by mbien on 28.03.18.
//

#ifndef PUTGEON_MESH_H
#define PUTGEON_MESH_H

#include <cstdint>
#include <cmath>
#include <string>
#include <vector>
#include <glm/glm.hpp>
#include <BulletCollision/CollisionShapes/btCollisionShape.h>


typedef unsigned int GLuint;

class Mesh {
public:
    Mesh();
    ~Mesh();
    bool loadFile(std::string file);

    std::vector<glm::vec3> vertices;
    std::vector<glm::vec3> normals;
    std::vector<glm::vec3> tangents;
    std::vector<glm::vec3> bitangents;
    std::vector<glm::vec2> texCoords;
    GLuint vertexBuffer;
    GLuint normalsBuffer;
    GLuint texCoordsBuffer;
    GLuint tangentBuffer;
    GLuint bitangentBuffer;
    btCollisionShape* collisionShape;


    GLuint vao;

    int getSize();
};


#endif //PUTGEON_MESH_H
