cmake_minimum_required(VERSION 3.9)
project(PUTgeon)

set(CMAKE_CXX_STANDARD 11)

find_package (OpenGL REQUIRED)
find_package(GLEW REQUIRED)
find_package(assimp REQUIRED)
find_package(Bullet REQUIRED)

include_directories(${OPENGL_INCLUDE_DIR} ${GLEW_INCLUDE_DIR} ${ASSIMP_INCLUDE_DIRS} ${BULLET_INCLUDE_DIRS})

add_executable(PUTgeon main.cpp mesh.cpp mesh.h texture.cpp texture.h engine.cpp engine.h model.cpp model.h material.cpp material.h lodepng.cpp lodepng.h shaderprogram.cpp shaderprogram.h cell.cpp cell.h map.cpp map.h actor.cpp actor.h physics.cpp physics.h)

target_link_libraries(PUTgeon ${OPENGL_LIBRARIES} glfw ${GLEW_LIBRARIES} ${ASSIMP_LIBRARIES} ${BULLET_LIBRARIES})