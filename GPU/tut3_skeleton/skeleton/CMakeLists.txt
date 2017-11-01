# CMakeLists.txt
cmake_minimum_required(VERSION 2.8)
project( tut3)

set( CMAKE_CURRENT_BINARY_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set( ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set( SRC_DIR ${ROOT_DIR}/src)

set(SOURCE
   ${SRC_DIR}/norm.cpp
)

if( APPLE OR UNIX)
  set( CMAKE_CXX_FLAGS "-g -W -Wall -std=c++14 -Wno-unused-variable  -Wno-unused-parameter")
endif()

add_executable( tut3 ${SOURCE})
