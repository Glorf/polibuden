//
// Created by mbien on 28.03.18.
//

#ifndef PUTGEON_TEXTURE_H
#define PUTGEON_TEXTURE_H

#include <iostream>

typedef unsigned int GLuint;

class Texture {
    GLuint textureId;
    unsigned int width;
    unsigned int height;
public:
    Texture();
    ~Texture();
    bool loadPNG(std::string filename);
    GLuint getId();
};


#endif //PUTGEON_TEXTURE_H
