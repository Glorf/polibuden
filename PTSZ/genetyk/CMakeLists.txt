cmake_minimum_required(VERSION 3.15)
project(genetyk)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_C_FLAGS -Ofast)

#Static start

set(CMAKE_FIND_LIBRARY_SUFFIXES ".a")
set(CMAKE_EXE_LINKER_FLAGS "-static-libgcc -static-libstdc++")

add_executable(genetyk main.cpp task.cpp task.h instance.cpp instance.h solver.cpp solver.h solution.cpp solution.h helpers.h helpers.cpp)