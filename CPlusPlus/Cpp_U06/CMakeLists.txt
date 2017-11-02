cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung6)
# Füge Source-Files hinzu
set(SRC_FILES node.cpp)
set(SRC_FILES program.cpp ${SRC_FILES})
set(SRC_FILES valuenode.cpp ${SRC_FILES})
set(SRC_FILES operators.cpp ${SRC_FILES})
set(SRC_FILES valuenode.hpp ${SRC_FILES})
set(SRC_FILES operators.hpp ${SRC_FILES})
set(SRC_FILES node.hpp ${SRC_FILES})
# Erzeuge Binary
add_executable(Uebung6 ${SRC_FILES})