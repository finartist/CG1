cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung3)
# Füge Source-Files hinzu
set(SRC_FILES object.cpp)
set(SRC_FILES program.cpp ${SRC_FILES})
set(SRC_FILES dynamicobjectlist.hpp ${SRC_FILES})
set(SRC_FILES object.h ${SRC_FILES})
# Erzeuge Binary
add_executable(Uebung3 ${SRC_FILES})