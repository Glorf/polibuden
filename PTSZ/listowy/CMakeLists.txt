cmake_minimum_required(VERSION 3.15)
project(list_that_hurts C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_FLAGS -Ofast)

#Static start

set(CMAKE_FIND_LIBRARY_SUFFIXES ".a")
set(CMAKE_EXE_LINKER_FLAGS "-static-libgcc -static-libstdc++")

add_executable(list_that_hurts main.c)

set_target_properties(list_that_hurts PROPERTIES LINK_SEARCH_START_STATIC 1)
set_target_properties(list_that_hurts PROPERTIES LINK_SEARCH_END_STATIC 1)