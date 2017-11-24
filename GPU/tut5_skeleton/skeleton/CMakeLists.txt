# CMakeLists.txt
cmake_minimum_required(VERSION 2.8)
project( tut5)

set( CMAKE_CURRENT_BINARY_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set( ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set( SRC_DIR ${ROOT_DIR}/src)

set(SOURCE
   ${SRC_DIR}/main.cpp
)

if( APPLE OR UNIX)
  set( CMAKE_CXX_FLAGS "-g -W -Wall -std=c++14 -Wno-unused-variable  -Wno-unused-parameter")
endif()

if( UNIX AND NOT APPLE)
  set( CMAKE_CXX_FLAGS "-pthread")
endif()

add_executable( tut5 ${SOURCE})
