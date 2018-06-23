//
// Created by mbien on 28.03.18.
//

#include "mesh.h"
#include "shaderprogram.h"
#include <iostream>
#include <glm/glm.hpp>
#include <vector>
#include <assimp/Importer.hpp>
#include <assimp/scene.h>
#include <assimp/postprocess.h>
#include <GL/glew.h>
#include <BulletCollision/CollisionShapes/btConvexTriangleMeshShape.h>
#include <BulletCollision/CollisionShapes/btTriangleMesh.h>


Mesh::Mesh() {}

Mesh::~Mesh() {
    glDeleteBuffers(1, &vertexBuffer);
    glDeleteBuffers(1, &texCoordsBuffer);
    glDeleteBuffers(1, &normalsBuffer);
    glDeleteBuffers(1, &tangentBuffer);
    glDeleteBuffers(1, &bitangentBuffer);

    glDeleteVertexArrays(1,&vao);
}

bool Mesh::loadFile(std::string path) {

    std::cout<<path<<std::endl;

    Assimp::Importer importer;
    const aiScene* scene = importer.ReadFile(path, aiProcess_CalcTangentSpace|aiProcess_Triangulate); //Calculate tangents and bitangents on load
    if(!scene) {
        std::cerr << importer.GetErrorString() << std::endl;
        return false;
    }
    const aiMesh* mesh = scene->mMeshes[0]; //always only one mesh

    // Fill model data
    vertices.reserve(mesh->mNumVertices);
    texCoords.reserve(mesh->mNumVertices);
    normals.reserve(mesh->mNumVertices);
    tangents.reserve(mesh->mNumVertices);
    bitangents.reserve(mesh->mNumVertices);

    for(unsigned int i=0; i<mesh->mNumVertices; i++){
        aiVector3D &pos = mesh->mVertices[i];
        aiVector3D &tex = mesh->mTextureCoords[0][i];
        aiVector3D &n = mesh->mNormals[i];
        aiVector3D &t = mesh->mTangents[i];
        aiVector3D &b = mesh->mBitangents[i];

        vertices.emplace_back(glm::vec3(pos.x, pos.y, pos.z));
        texCoords.emplace_back(glm::vec2(tex.x, tex.y));
        normals.emplace_back(glm::vec3(n.x, n.y, n.z));
        tangents.emplace_back(glm::vec3(t.x, t.y, t.z));
        bitangents.emplace_back(glm::vec3(b.x, b.y, b.z));
    }

    //add collision shape
    btTriangleMesh *triangleMesh = new btTriangleMesh;
    for(int i=0; i<vertices.size(); i+=3) {
        btVector3 vector[3];
        for(int j=0; j<3; j++)
            vector[j] = btVector3(vertices[i+j].x, vertices[i+j].y, vertices[i+j].z);
        triangleMesh->addTriangle(vector[0], vector[1], vector[2]);
    }

    collisionShape = new btConvexTriangleMeshShape(triangleMesh);

    glGenBuffers(1, &vertexBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
    glBufferData(GL_ARRAY_BUFFER, vertices.size() * sizeof(glm::vec3), &vertices[0], GL_STATIC_DRAW);

    glGenBuffers(1, &texCoordsBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, texCoordsBuffer);
    glBufferData(GL_ARRAY_BUFFER, texCoords.size() * sizeof(glm::vec2), &texCoords[0], GL_STATIC_DRAW);

    glGenBuffers(1, &normalsBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, normalsBuffer);
    glBufferData(GL_ARRAY_BUFFER, normals.size() * sizeof(glm::vec3), &normals[0], GL_STATIC_DRAW);

    glGenBuffers(1, &tangentBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, tangentBuffer);
    glBufferData(GL_ARRAY_BUFFER, tangents.size() * sizeof(glm::vec3), &tangents[0], GL_STATIC_DRAW);

    glGenBuffers(1, &bitangentBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, bitangentBuffer);
    glBufferData(GL_ARRAY_BUFFER, bitangents.size() * sizeof(glm::vec3), &bitangents[0], GL_STATIC_DRAW);


    return true;
}

int Mesh::getSize() {
    return (int)vertices.size();
}