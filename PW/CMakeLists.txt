cmake_minimum_required(VERSION 3.9)
project(Projekt C)

set(CMAKE_C_STANDARD 99)

add_executable(client inf132191_k.c inf132191_h.h)
add_executable(server inf132191_s.c inf132191_h.h)