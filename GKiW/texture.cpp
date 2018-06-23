//
// Created by mbien on 28.03.18.
//

#include "texture.h"
#include <cstring>
#include <GL/glew.h>
#include "lodepng.h"


Texture::Texture() {

}

Texture::~Texture() {
    glDeleteTextures(1, &textureId);
}

bool Texture::loadPNG(std::string filename) {
    std::vector<unsigned char> image;

    unsigned error = lodepng::decode(image, width, height, filename);

    if(error) std::cout << "decoder error " << error << ": " << lodepng_error_text(error) << std::endl;

    glGenTextures(1, &textureId);
    glBindTexture(GL_TEXTURE_2D, textureId);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image.data());
    glGenerateMipmap(textureId);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    GLfloat fLargest;
    glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, &fLargest);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, fLargest);

    return true;
}

GLuint Texture::getId() {
    return this->textureId;
}