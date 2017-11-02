cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung7)
# Füge Source-Files hinzu
set(SRC_FILES main.cpp)
set(SRC_FILES myvector.cpp ${SRC_FILES})
set(SRC_FILES myvector.h ${SRC_FILES})

# Erzeuge Binary
add_executable(Uebung7 ${SRC_FILES})