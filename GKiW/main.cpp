#include <iostream>
#include <GL/glew.h>
#include <GLFW/glfw3.h>

#include "engine.h"
#include "model.h"

int main() {

    if(!Engine::get().init()) return -1;
    if(!Engine::get().setWindow(1024, 768, "PUTgeon")) return -1;
    if(!Engine::get().addShaderProgram("default", "VertexShader.glsl", "FragmentShader.glsl")) return -1;
    if(!Engine::get().loadMap("map1")) return -1;

    Engine::get().clearToColor(0.3f, 0.3f, 0.3f, 0.0f);
    Engine::get().setMouseSpeed(0.005); //TODO: move to settings.cfg
    Engine::get().run();

    Engine::get().destruct();
    return 0;
}