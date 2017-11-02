cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung8)
# Füge Source-Files hinzu
set(SRC_FILES program.cpp)
set(SRC_FILES Spacestuff.h ${SRC_FILES})

# Erzeuge Binary
add_executable(Uebung8 ${SRC_FILES})