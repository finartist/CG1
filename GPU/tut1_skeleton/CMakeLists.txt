# CMakeLists.txt
cmake_minimum_required(VERSION 2.8)
project( tut1)

set( CMAKE_CURRENT_BINARY_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set( ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set( SRC_DIR ${ROOT_DIR}/src)

set(SOURCE
   ${SRC_DIR}/main.cpp
)

set( CMAKE_C_FLAGS "-g -W -Wall -Wno-unused-parameter")

add_executable( tut1 ${SOURCE})
