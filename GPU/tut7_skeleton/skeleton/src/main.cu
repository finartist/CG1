/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : tutorial 7
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: CUDA basics
///
////////////////////////////////////////////////////////////////////////////////////////////////////

#include <iostream>

#include "cuda_util.h"

////////////////////////////////////////////////////////////////////////////////////////////////////
// add constant to vector
////////////////////////////////////////////////////////////////////////////////////////////////////
__global__
void
vecAddC(float* data, const unsigned int n, const float val) {

	data[threadIdx.x] += val;
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// program entry point
////////////////////////////////////////////////////////////////////////////////////////////////////
int
main(int /*argc*/, char** /*argv*/) {

	const unsigned int n = 16;
	float* data = (float*)malloc(sizeof(float) * n);
	for (unsigned int i = 0; i < n; ++i) {
		data[i] = (float)i;
	}

	// get number of CUDA devices
	int deviceCount = 0;
	cudaGetDeviceCount(&deviceCount);
	std::cerr << deviceCount << std::endl;

	// query the device properties
	cudaDeviceProp props;
	cudaGetDeviceProperties(&props, 0);
	printDeviceProps(props);

	// set the device
	cudaSetDevice(0);

	// initialize memory
	float* device_data;

	// allocate device memory
	cudaMalloc((void**)&device_data, sizeof(float) *n);

	// copy to device memory
	cudaMemcpy(device_data, data, sizeof(float) * n, cudaMemcpyHostToDevice);

	// determine thread layout


	// run kernel
	//make 16 threads and do vecAddC in all of them
	vecAddC << < 1, 16 >> >(device_data, n, 5);
	cudaDeviceSynchronize();

	// copy result back to host
	cudaMemcpy(data, device_data, sizeof(float) * n, cudaMemcpyDeviceToHost);

	// output
	for (unsigned int i = 0; i < n; ++i) {
		std::cout << "data[" << i << "] = " << data[i] << '\n';
	}

	// clean up device memory
	cudaFree(device_data);

	// clean up host memory
	free(data);

	std::getchar();
	return EXIT_SUCCESS;
}