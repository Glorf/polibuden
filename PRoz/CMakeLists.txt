cmake_minimum_required(VERSION 2.8)
project(impossibility)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_COMPILER mpicxx)
set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

add_executable(impossibility main.cpp guide.cpp guide.h)
