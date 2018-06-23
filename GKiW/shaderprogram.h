//
// Created by mbien on 28.04.18.
//

#ifndef PUTGEON_SHADERPROGRAM_H
#define PUTGEON_SHADERPROGRAM_H

#include <string>

typedef int GLint;
typedef unsigned int GLuint;

class ShaderProgram {
    GLuint vertexId;
    GLuint fragmentId;
    GLuint programId;
    std::string fileToString(const std::string &file);
    bool compileShader(const std::string &code, GLuint shaderId);
    bool ready;
public:
    ShaderProgram(const std::string &vertexFile, const std::string &fragmentFile);
    ~ShaderProgram();
    GLuint getId();
    GLuint getUniformLocation(std::string varname);
    GLuint getAttribLocation(std::string varName);
    bool isReady();
    bool use();
};


#endif //PUTGEON_SHADERPROGRAM_H
