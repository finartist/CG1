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
#include <chrono>
#include <ctime>
#include <cassert>
#include <algorithm>


////////////////////////////////////////////////////////////////////////////////////////////////////
//! Merge sort serial
////////////////////////////////////////////////////////////////////////////////////////////////////
template<typename T>
void
mergesort( T* data, T* last, T* scratch ) {

	const int nmin = 16;
	int n = last - data;
	if(n > nmin)
	{
		mergesort(data, data + n / 2, scratch);
		mergesort(data + n / 2, last, scratch);
		merge(data, data + n / 2, data + n / 2, last, scratch);
	}
    else
	{
		//sort smaller lists with anonther algorithm what might be more efficient as merge sort
		std::sort(data, last);
		return;
	}
}

template<typename T> void merge(T* dataleft, T* lastleft, T* dataright, T* lastright, T*scratch){
	int l = 0;
	int r = 0;

	//always take the smaller element from the left or right array
	while(dataleft + l != lastleft && dataright + r != lastright)
	{
		if(dataleft[l] <= dataright[r])
		{
			//std::move to avoid copying whatever T is (might be bigger than an int)
			scratch[l + r] = std::move(dataleft[l]);
			l++;
		}
		else
		{
			scratch[l + r] = std::move(dataright[r]);
			r++;
		}
	}

	//if elements from one array are left, add them at the end
	while(dataleft + l != lastleft)
	{
		scratch[l + r] = std::move(dataleft[l]);
		l++;
	}

	while(dataright + r != lastright)
	{
		scratch[l + r] = std::move(dataright[r]);
		r++;
	}

	//copy sorted elements to the original data array
	//std::memcpy(dataleft, scratch, r+l); //does not work :( ??
	std::copy(scratch, scratch + l + r, dataleft);
	//for(int i = 0;  i < r+l; i++)
	//{
	//	//std::cout << scratch[i] << " # " << dataleft[i] << std::endl;
	//}
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//! Merge sort parallel
// split up until number of cores is reached
////////////////////////////////////////////////////////////////////////////////////////////////////
template<typename T>
void
mergesortParallel( T* data, T* last, T* scratch, const int num_threads) {
	
	int n = last - data;
	std::vector<std::thread> threads;
	
	//divide data in num_threads chunks
	int chunk_size = n / num_threads;
	assert(0 == (last - data) % num_threads);
	assert(0 == num_threads % 2);

	//run serial mergesort for each chunk
	for (int i = 0; i < num_threads; i++)
	{
		//scratch is one array -> avoid race condition by partition it in chunk size parts
		threads.push_back(std::thread(mergesort<T>, data + i*chunk_size, data + (i+1)*chunk_size, scratch + i*chunk_size));
	}

	//join threads
	for(auto& th : threads)
	{
		th.join();
	}

	//merge results of threads as long as at least 2 chunks are left to merge
	int remaining_blocks = num_threads;
	while(remaining_blocks > 1)
	{
		for(int i = 0; i < remaining_blocks; i = i + 2)
		{
			merge(data + i*chunk_size, data + (i + 1)*chunk_size, data + (i + 1)*chunk_size, data + (i + 2)*chunk_size, scratch);
		}
		remaining_blocks /= 2;
		chunk_size *= 2;
	}

}


////////////////////////////////////////////////////////////////////////////////////////////////////
// program entry point
////////////////////////////////////////////////////////////////////////////////////////////////////
int
main( int /*argc*/, char** /*argv*/ ) {

  std::srand( std::time(0));

  // allocate memory
  int K = 100;
  int n = 4096*8;
  int* data = (int*) malloc( n * sizeof(int));
  int* scratch = (int*) malloc( n * sizeof(int));
  std::generate( data, data + n, std::rand);

  // parallel solution
  int* datapar = (int*)malloc(n * sizeof(int));
  memcpy(datapar, data, n * sizeof(int));

  // reference solution
  int* dataref = (int*) malloc( n * sizeof(int));
  memcpy( dataref, data, n * sizeof(int));
  std::sort( dataref, dataref+n);
  
  double timesumser = 0;
  double timesumpar = 0;

  for(int i = 0; i < K; i++)
  {
	  // start timing
	  clock_t tstartser = clock();
	  mergesort(data, data + n, scratch);
	  // end timing
	  clock_t tendser = clock();
	  double telapsedser = (double)(tendser - tstartser) / CLOCKS_PER_SEC;
	  timesumser += telapsedser;

	  // run parallel merge sort
	  unsigned int num_threads = std::thread::hardware_concurrency();
	  //std::cout << "num_threads = " << num_threads << std::endl;
	  clock_t tstartpar = clock();
	  mergesortParallel(datapar, datapar + n, scratch, num_threads);
	  clock_t tendpar = clock();
	  double telapsedpar = (double)(tendpar - tstartpar) / CLOCKS_PER_SEC;
	  timesumpar += telapsedpar;

	  // check correctness of result
	  bool correctser = true;
	  for (int i = 0; i < n; ++i) {
		  //std::cout << dataref[i] << " / " << data[i] << std::endl;
		  correctser &= (dataref[i] == data[i]);
	  }
	  std::cout << "Mergesort serial " << ((correctser) ? "succeeded." : "failed.") << " in " << telapsedser << " seconds." << std::endl;

	  // check correctness of result
	  bool correctpar = true;
	  for (int i = 0; i < n; ++i) {
		  //std::cout << dataref[i] << " / " << data[i] << std::endl;
		  correctpar &= (dataref[i] == datapar[i]);
	  }
	  std::cout << "Mergesort parallel " << ((correctpar) ? "succeeded." : "failed.") << " in " << telapsedpar << " seconds." << std::endl;

	  std::generate(data, data + n, std::rand);
	  memcpy(datapar, data, n * sizeof(int));
	  memcpy(dataref, data, n * sizeof(int));
	  std::sort(dataref, dataref + n);
  }

  std::cout << "Mean serial time " << timesumser/K << std::endl;
  std::cout << "Mean parallel time " << timesumpar/K << std::endl;
  
  // clean up
  free( data);
  free( scratch);
  free( dataref);

  return EXIT_SUCCESS;
}
