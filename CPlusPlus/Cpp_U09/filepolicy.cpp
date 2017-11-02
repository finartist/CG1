#include <iostream>
#include <fstream>
#include "filepolicy.hpp"



void FilePolicy::write(const std::string& message)
{
	// app makes the file to write to the end of file (append)
	std::ofstream out(filename, std::ios::app);
	out << message << std::endl;
	// leert den ofstream Buffer
	out.flush();
	out.close();
}