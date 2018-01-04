cmake_minimum_required(VERSION 3.8)
project(mrowka)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp problem.cpp problem.h)
add_executable(mrowka ${SOURCE_FILES})