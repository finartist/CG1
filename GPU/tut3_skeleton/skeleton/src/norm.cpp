/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : lecture 3
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: race conditions
///
////////////////////////////////////////////////////////////////////////////////////////////////////

// includes, system
#include <iostream>
#include <iomanip>
#include <vector>
#include <thread>
#include <cstdlib>
#include <cmath>
#include <mutex>
#include <limits>

std::mutex mutex_sum;

////////////////////////////////////////////////////////////////////////////////////////////////////
// Parallel worker for norm computation
////////////////////////////////////////////////////////////////////////////////////////////////////
template< typename T >
void
computeNormWorker( unsigned int tid, unsigned int num_threads, const T* a, int n, T& sum) {

  unsigned int delta = n / num_threads;
  unsigned int i1 = tid * delta;
  unsigned int i2 = (tid+1) * delta;

  T sum_loc = 0;
  for( unsigned int i = i1; i < i2; ++i) {
    sum_loc += a[i] * a[i];
  }

  mutex_sum.lock();
  sum += sum_loc;
  mutex_sum.unlock();
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// Compute norm of vector
////////////////////////////////////////////////////////////////////////////////////////////////////
template< typename T >
T
computeNorm( unsigned int num_threads, const T* a, const unsigned int n) {

  T sum = 0.0;

  //dynamic amount of threads, so hold them all in an vector
  std::vector<std::thread> threads;

  for (int i = 0; i < num_threads; ++i) {
	  //computeNormWorker is Template so use the T
	  //sum is a reference, but for threads one has to use std::ref to give a thread a referenec
	  threads.push_back(std::thread(computeNormWorker<T>, i, num_threads, a, n, std::ref(sum)));
  }

  for (auto& th : threads) {
	  th.join();
  }

  return std::sqrt( sum);
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// program entry point
////////////////////////////////////////////////////////////////////////////////////////////////////
int
main( int /*argc*/, char** /*argv*/ ) {

  const unsigned int n = 8192;
  std::cout << "n = " << n << std::endl;
  typedef float DataType;

  // allocate array
  DataType* a = (DataType*) malloc(sizeof(DataType) * n);
  // initialize array
  for (int i = 0; i < n; ++i) {
	  a[i] = 1.0 / ((DataType) (i + 1));
  }
  // TODO

  // compute norm
  DataType norm1 = computeNorm( 1, a, n);
  DataType norm2 = computeNorm(2, a, n);
  DataType norm4 = computeNorm(4, a, n);

  //all of the below have different results
  //Why? -> floating point problems
  //floating points look like +- 1.m1m2m3m4m5m6...mn b^k where (m1..mn are digits = mantisse, b is base and k exponent)
  //that results in smaller distances between numbers for small values and increasing distance for higher values
  //so why using floating points? if i have high values, do not need that high precision -> relative error is always the same (like xx% error), absolute error rising
  // machine precision epsilon= kleinste Zahl u der reelen Zahlen, so dass in Gleitkommazahlen 1+u > 1 (u wird nicht weggerundet)
  //||G(a+b)| - |a+b|| < epsilon
  //for each operation can have a error of epsilon -> (2*n + 1)*epsilon error
  std::cerr << std::scientific << std::setprecision( std::numeric_limits<DataType>::digits10 );
  std::cerr << "1 thread: norm1(a) = " << norm1 << std::endl;
  std::cerr << "2 threads: norm2(a) = " << norm2 << std::endl;
  std::cerr << "4 threads: norm4(a) = " << norm4 << std::endl;
  std::cerr << "max error: " << std::numeric_limits<DataType>::epsilon() * (2 * n + 1) << std::endl;

  // cleanup
  free(a);
  return EXIT_SUCCESS;
}
