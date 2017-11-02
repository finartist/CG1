cmake_minimum_required (VERSION 2.6)
# Projektname
project(ScopeTimer)

file(GLOB SOURCES
    src/*.hpp
    src/*.cpp
)

# Erzeuge Binary
add_executable(ScopeTimer ${SOURCES})