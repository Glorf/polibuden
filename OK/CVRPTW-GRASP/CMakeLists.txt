cmake_minimum_required(VERSION 3.8)
project(heurystyka)

set(CMAKE_CXX_STANDARD 11)

IF(CMAKE_COMPILER_IS_GNUCC)
    set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -O0 -Wall")
    set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} -O0 -Wall")
ENDIF(CMAKE_COMPILER_IS_GNUCC)

set(SOURCE_FILES main.cpp problem.cpp problem.h)
add_executable(heurystyka ${SOURCE_FILES})