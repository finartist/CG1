cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung11)
# Füge Source-Files hinzu
set(SRC_FILES main.cpp fleet.h spaceshipfactory.h spaceships.h spaceshipfactory.cpp fleet.cpp)

# Erzeuge Binary
add_executable(Uebung11 ${SRC_FILES})