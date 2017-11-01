/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : Tutorial 1
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: caching
///
////////////////////////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int
main( int argc, char** argv) {

  if( argc < 2) {
    printf( "Error: Specify data size.\n");
    return EXIT_FAILURE;
  }
  unsigned int data_size = (unsigned int) atoi( argv[1]);
  printf( "data_size = %i\n", data_size);

  // allocate data on heap
  int* data = (int*) malloc(sizeof(int) * data_size * data_size);

  // initialize data
  for (int i = 0; i < data_size*data_size; ++i) {
	  data[i] = i;
  }

  // start timing
  clock_t tstart = clock();

  // for reliable timing
  for( unsigned int k = 0; k < 1000; ++k) {

  //pragma to choose if row or column major order
#if 0
  // "process" data row-major order rows = j, columns = i
  // row major order is faster because data is already taken at once and brought to cache
  // for column major you have to jump and always wait for the data for each element (latency)
  // really is noticeable when arary size is bigger than cache size
	  for (int j = 0; j < data_size; ++j) {
		  for (int i = 0; i < data_size; ++i) {
			  data[j * data_size + i] = data[j * data_size + i] * data[j * data_size + i];
			  //printf("i,j = %i / %i :: %i\n", i, j, j * data_size + i);
		  }
	  }
#else
  // "process" data column-major order
	  for (int i = 0; i < data_size; ++i) {
		  for (int j = 0; j < data_size; ++j) {
			  data[j * data_size + i] = data[j * data_size + i] * data[j * data_size + i];
			  //printf("i,j = %i / %i :: %i\n", i, j, j * data_size + i);
		  }
	  }
#endif

  }

  // end timing
  clock_t tend = clock();
  // final time
  double telapsed = (double)(tend - tstart) / CLOCKS_PER_SEC;
  printf( "time elaspsed = %2.4f sec\n", telapsed);

  // cleanup memory
  free(data);

  return EXIT_SUCCESS;
}
