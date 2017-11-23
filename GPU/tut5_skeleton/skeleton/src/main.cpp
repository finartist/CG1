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

	//copy sorted elements back to the original data array
	std::copy(scratch, scratch + l + r, dataleft);
}

template<typename T>
void
merge2(T* data1, T* last1, T* data2, T* last2, T* scratch) {

	// merge
	while (data1 != last1 && data2 != last2) {
		T*& which = (*data1 < *data2) ? data1 : data2;
		*scratch++ = std::move(*which++);
	}
	// copy remainder (obviously, only one will have an effect)
	std::move(data1, last1, scratch);
	std::move(data2, last2, scratch);
}

template<typename T> void mergeParallel(T* left, T* leftlast, T* right, T* rightlast, T* scratch, int remaining_threads)
{
	//end recursion
	const int nmin = 16;
	if((rightlast - right) + (leftlast -left) <= nmin)
	{
		merge2(left, leftlast, right, rightlast, scratch);
		return;
	}

	T* x = nullptr;
	T* xlast = nullptr;
	T* y = nullptr;
	T* ylast = nullptr;

	//always use the longer array to split in half
	if (leftlast - left < rightlast - right)
	{
		x = right;
		xlast = rightlast;
		y = left;
		ylast = leftlast;
	}
	else
	{
		x = left;
		xlast = leftlast;
		y = right;
		ylast = rightlast;
	}

	//split x in half
	int x_n = xlast - x;
	int xhalf = x_n / 2;
	
	//search the first element in y to be bigger than the one at the center position of of x
	int y_index = std::upper_bound(y, ylast, x[xhalf]) - y;

	//merge the two halfs together
	if(remaining_threads > 0)
	{
		mergeParallel(x, x + xhalf, y, y + y_index, scratch, remaining_threads -1);
		std::thread t1(mergeParallel<T>, x + xhalf, xlast, y + y_index, ylast, scratch + xhalf + y_index, remaining_threads-1);
		t1.join();
	}
	else
	{
		merge2(x, x + xhalf, y, y + y_index, scratch);
		merge2(x + xhalf, xlast, y + y_index, ylast, scratch + xhalf + y_index);
	}
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
#if 0
		for(int i = 0; i < remaining_blocks; i = i + 2)
		{
			merge(data + i*chunk_size, data + (i + 1)*chunk_size, data + (i + 1)*chunk_size, data + (i + 2)*chunk_size, scratch);
		}
#else
		threads.clear();
		for (int i = 0; i < remaining_blocks; i = i + 2)
		{
			threads.push_back(std::thread(mergeParallel<T>, data + i*chunk_size, data + (i + 1)*chunk_size, 
				data + (i + 1)*chunk_size, data + (i + 2)*chunk_size, scratch + i*chunk_size, num_threads / (remaining_blocks/2)));
		}
		for(int i = 0; i < remaining_blocks; i = i + 2)
		{
			threads[i / 2].join();
			std::move(scratch + i*chunk_size, scratch + (i + 2)*chunk_size, data + i*chunk_size);
		}
#endif
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
  int K = 1;
  int n = 4096 * 8;
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
	  std::cout << "mergesort serial" << std::endl;
	  // start timing
	  clock_t tstartser = clock(); //better use std::chrono::high_resolution_clock::now()
	  mergesort(data, data + n, scratch);
	  // end timing
	  clock_t tendser = clock();
	  double telapsedser = (double)(tendser - tstartser) / CLOCKS_PER_SEC;
	  timesumser += telapsedser;

	  std::cout << "mergesort parallel" << std::endl;
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
		  if (dataref[i] != datapar[i])
		  {
			  std::cout << dataref[i] << " / " << datapar[i] << std::endl;
		  }
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
