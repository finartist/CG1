cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung10)
# Füge Source-Files hinzu
set(SRC_FILES program.cpp hybridarray.hpp hybridarray.cpp)

# Erzeuge Binary
add_executable(Uebung10 ${SRC_FILES})