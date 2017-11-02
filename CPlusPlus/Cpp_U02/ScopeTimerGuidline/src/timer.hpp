#pragma once

#include <ctime>		// Use the function clock() to measure time.

/// \brief The ScopeTimer measures the time between construction and
///		destruction and writes it to the console.
///	\details On destruction the timer writes its "name" and the
///		time to std::clog.

// TODO: Write a class named ScopeTimer
//		   * Use an integer argument in constructor to give the time a "name"
//		     and store this number.
//		   * Get the point in time inside constructor and store it too.
//			 You can use the clock() function which is easy and platform
//			 independent. Feel free to inform yourself about this function.
//			 You may also use other methods as long as you can explain them.
//		   * Write a destructor which measures the time again, computes the
//		     difference and writes the output to clog (preferably in ms).

// basic structure of the class
// everything already declared in the header does not need to be declared in the .cpp
// visibility too!!

class Timer {
	int name;
	clock_t start;

public:
	Timer(int a);

	~Timer();
};