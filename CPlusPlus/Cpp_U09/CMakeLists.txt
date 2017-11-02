cmake_minimum_required (VERSION 2.6)
# Projektname
project(Uebung9)
# Füge Source-Files hinzu
set(SRC_FILES program.cpp)
set(SRC_FILES consolepolicy.cpp ${SRC_FILES})
set(SRC_FILES filepolicy.cpp ${SRC_FILES})
set(SRC_FILES logger.cpp ${SRC_FILES})
set(SRC_FILES consolepolicy.hpp ${SRC_FILES})
set(SRC_FILES filepolicy.hpp ${SRC_FILES})
set(SRC_FILES logger.hpp ${SRC_FILES})
set(SRC_FILES policy.hpp ${SRC_FILES})

# Erzeuge Binary
add_executable(Uebung9 ${SRC_FILES})