cmake_minimum_required(VERSION 3.9)
project(zus)

set(CMAKE_CXX_STANDARD 11)

find_package(Boost COMPONENTS system filesystem serialization REQUIRED)
include_directories(${Boost_INCLUDE_DIRS})

add_executable(zus main.cpp Portfel.h PortfelInwestycyjny.h Aktywa.h Fundusz.h Aktywa.cpp PortfelInwestycyjny.cpp FunduszZD.cpp FunduszZD.h FunduszBD.cpp FunduszBD.h Kontrakt.cpp Kontrakt.h Lokata.cpp Lokata.h Nieruchomosc.cpp Nieruchomosc.h Produkt.cpp Produkt.h IKE.cpp IKE.h IKZE.cpp IKZE.h)
target_link_libraries(zus ${Boost_LIBRARIES})