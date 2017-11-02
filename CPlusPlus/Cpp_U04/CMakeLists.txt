cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung4)
# Füge Source-Files hinzu
set(SRC_FILES TestObject.cpp)
set(SRC_FILES program.cpp ${SRC_FILES})
set(SRC_FILES RandomChar.cpp ${SRC_FILES})
set(SRC_FILES TestObject.h ${SRC_FILES})
set(SRC_FILES RandomChar.h ${SRC_FILES})
# Erzeuge Binary
add_executable(Uebung4 ${SRC_FILES})