#include "timer.hpp"
#include <iostream>

// TODO: Definition of constructor and destructor of the ScopeTimer.
// TODO: Write a class named ScopeTimer
//		   * Use an integer argument in constructor to give the time a "name"
//		     and store this number.
//		   * Get the point in time inside constructor and store it too.
//			 You can use the clock() function which is easy and platform
//			 independent. Feel free to inform yourself about this function.
//			 You may also use other methods as long as you can explain them.
//		   * Write a destructor which measures the time again, computes the
//		     difference and writes the output to clog (preferably in ms).


	// definition of the declaration made inthe header -> no class keyword etc. needed anymore, but Timer:: so it referes to the header
	// do not need to include ctime here, because already is in the header

	// start and a are not yet initialized, but they are declared in the header, so we do not need to do it here
	Timer::Timer(int a) {
		name = a;
		start = clock();
	}

	Timer::~Timer() {
		// gives out name and time in milliseconds
		std::clog << name << ": " << (clock() - start) / (CLOCKS_PER_SEC / 1000) << "\n";
	}
