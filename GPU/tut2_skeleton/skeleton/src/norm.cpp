/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : tutorial 2
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: race conditions
///
////////////////////////////////////////////////////////////////////////////////////////////////////

#include <iostream>
#include <vector>
#include <thread>
#include <cstdlib>
#include <cmath>
#include <mutex>
#include <atomic>

int sum = 0;
std::mutex summutex;
//std::atomic<int> atomicsum;

////////////////////////////////////////////////////////////////////////////////////////////////////
// add two vectors together
////////////////////////////////////////////////////////////////////////////////////////////////////

void
compute_norm( unsigned int tid, unsigned int num_threads, int* a, int n) {

	std::cout << tid << std::endl;

	//slice of the array we need for this thread
	int chunk_size = n / num_threads;
	int i1 = tid * chunk_size;
	int i2 = (tid + 1) * chunk_size;

	int suml = 0;

	for (int i = i1; i < i2; ++i) {
		suml += a[i] * a[i];
	}

	//use mutex to lock sum for other threads to avoid race conditions
	//alternative atomic variable atomicsum += suml;
	summutex.lock();
	sum += suml;
	summutex.unlock();
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// program entry point
////////////////////////////////////////////////////////////////////////////////////////////////////
int
main( int argc, char** argv ) {

	if (argc < 2) {
		printf("Error: Specify data size.\n");
		return EXIT_FAILURE;
	}
	unsigned int num_threads = (unsigned int)atoi(argv[1]);

  const unsigned int n = 8192;
  //const unsigned int num_threads = 2;

  //dynamic amount of threads, so hold them all in an vector
  std::vector<std::thread> threads;

  // allocate memory
  int* a = (int*)malloc(sizeof(int) * n);
  //int sum = 0;

  for (int i = 0; i < n; ++i) {
	  a[i] = 1;
  }

  //if want to give a reference to a thread, use std::ref() in the thread constructor
  //create threads
  for (int i = 0; i < num_threads; ++i) {
	  threads.push_back(std::thread(compute_norm, i, num_threads, a, n));
  }

  //join threads
  /*for (int i = 0; i < num_threads; ++i) {
	  threads.back().join();
	  threads.pop_back();
  }*/

  //have to make auto an explicit reference as threads do have private copy constructor
  for (auto& th : threads) {
	  th.join();
  }

  std::cerr << "norm(a) = " << std::sqrt( (float) sum) << std::endl;

  // cleanup memory
  free(a);

  return EXIT_SUCCESS;
}
