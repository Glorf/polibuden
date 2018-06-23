//
// Created by mbien on 28.04.18.
//

#include <fstream>
#include <sstream>
#include <GL/glew.h>
#include <vector>
#include <iostream>
#include "shaderprogram.h"

ShaderProgram::ShaderProgram(const std::string &vertexFile, const std::string &fragmentFile) {
    ready = false;
    vertexId = glCreateShader(GL_VERTEX_SHADER);
    fragmentId = glCreateShader(GL_FRAGMENT_SHADER);

    std::string vertexShader = fileToString(vertexFile);
    std::string fragmentShader = fileToString(fragmentFile);

    if(!compileShader(vertexShader, vertexId) || !compileShader(fragmentShader, fragmentId)) {
        std::cerr<<"Failed to compile shaders!"<<std::endl;
        return;
    }

    programId = glCreateProgram();
    glAttachShader(programId, vertexId);
    glAttachShader(programId, fragmentId);
    glLinkProgram(programId);

    // Check the program
    int logLength;
    glGetProgramiv(programId, GL_INFO_LOG_LENGTH, &logLength);
    if (logLength > 0) {
        std::vector<char> ProgramErrorMessage(logLength + 1);
        glGetProgramInfoLog(programId, logLength, nullptr, &ProgramErrorMessage[0]);
        std::cerr << &ProgramErrorMessage[0] << std::endl;
        return;
    }

    ready = true;
}

ShaderProgram::~ShaderProgram() {
    glDetachShader(programId, vertexId);
    glDetachShader(programId, fragmentId);

    glDeleteShader(vertexId);
    glDeleteShader(fragmentId);

    glDeleteProgram(programId);
}

GLuint ShaderProgram::getId() {
    return programId;
}

GLuint ShaderProgram::getUniformLocation(std::string varname) {
    return (GLuint)glGetUniformLocation(programId, varname.c_str());
}

GLuint ShaderProgram::getAttribLocation(std::string varname) {
    return (GLuint)glGetAttribLocation(programId, varname.c_str());
}

bool ShaderProgram::use() {
    glUseProgram(programId);
    return true;
}

bool ShaderProgram::isReady() {
    return ready;
}

std::string ShaderProgram::fileToString(const std::string &file) {
    std::ifstream plik(file);

    if(plik.is_open()) {
        std::stringstream buffer;
        buffer << plik.rdbuf();
        std::string result = buffer.str();
        plik.close();
        return result;
    }
    else {
        std::cerr << "Cannot open " + file <<std::endl;
        return "";
    }
}

bool ShaderProgram::compileShader(const std::string &code, GLuint shaderId) {
    int logLength;
    char const *text = code.c_str();
    glShaderSource(shaderId, 1, &text, nullptr);
    glCompileShader(shaderId);

    glGetShaderiv(shaderId, GL_INFO_LOG_LENGTH, &logLength);
    if (logLength > 0) {
        std::vector<char> VertexShaderErrorMessage(logLength + 1);
        glGetShaderInfoLog(shaderId, logLength, nullptr, &VertexShaderErrorMessage[0]);
        std::cerr << &VertexShaderErrorMessage[0] << std::endl;
        return false;
    }

    return true;
}