/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : tutorial 9
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: CUDA convolution
///
////////////////////////////////////////////////////////////////////////////////////////////////////

// includes, system
#include <iostream>
#include <algorithm>
#include <chrono>

typedef std::chrono::time_point<std::chrono::high_resolution_clock> tpoint;

// includes, project
#include "cuda_util.h"
#include "kernel_separable.h"
#include "image.h"

// host implementation
extern void
convSeparableHost( float* kdata, const int& kernel_supp, const Image& image, Image& image_conv);

////////////////////////////////////////////////////////////////////////////////////////////////////
// convolution
////////////////////////////////////////////////////////////////////////////////////////////////////
//improve performance with setting "Apron size" == kernel_supp_half as template parameter
template<int kernel_supp_half>
__global__
void
convSeparable( float* kernel,
               float* image, float* image_conv, const unsigned int image_size) {

	int pixel_x = blockDim.x*blockIdx.x + threadIdx.x;
	int pixel_y = blockDim.y*blockIdx.y + threadIdx.y;

	__shared__ float weights[2*kernel_supp_half +1];
	if(threadIdx.x < 2*kernel_supp_half+1)
	{
		weights[threadIdx.x] = kernel[threadIdx.x];
	}

	// reinterpret weights as kernel (only works if memory layout is the same)
	// useful to have code available without rewriting it every time
	KernelSeperable<kernel_supp_half>* kernelshared = (KernelSeperable<kernel_supp_half> *) &weights;
	kernelshared->apply(pixel_x, pixel_y, image, image_conv, image_size);

#if 0
	int ikx = 0;
	int iky = 0;
	float val = 0.0;
	//kernelsupp_half = apron size
    for(int iy = pixel_y - kernel_supp_half; iy <= kernel_supp_half + pixel_y; ++iy, iky++)
    {
		float wy = kernel[iky];
	    for(int ix = pixel_x -kernel_supp_half; ix <= kernel_supp_half + pixel_x; ++ix, ikx++)
	    {
			if(ix >= 0 && ix < image_size && iy >= 0 && iy < image_size)
			{
				float wx = weights[ikx];
				val += wx * wy * image[pixel_y * image_size + pixel_x];
			}
	    }
		ikx = 0;
    }
#endif
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// initialize Cuda device
////////////////////////////////////////////////////////////////////////////////////////////////////
bool
initDevice( int& device_handle, int& max_threads_per_block) {

  int deviceCount = 0;
  checkErrorsCuda( cudaGetDeviceCount(&deviceCount));

  if( 0 == deviceCount) {
    std::cerr << "initDevice() : No CUDA device found." << std::endl;
    return false;
  }

  // one could implement more complex logic here to find the fastest device
  if( deviceCount > 1) {
    std::cerr << "initDevice() : Multiple CUDA devices found. Using first one." << std::endl;
  }

  // set the device
  checkErrorsCuda( cudaSetDevice( device_handle));

  cudaDeviceProp device_props;
  checkErrorsCuda( cudaGetDeviceProperties(&device_props, device_handle));
  max_threads_per_block = device_props.maxThreadsPerBlock;

  return true;
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// program entry point
////////////////////////////////////////////////////////////////////////////////////////////////////
int
main( int /*argc*/, char** /*argv*/ ) {

  const int kernel_supp = 5;
  const int kernel_supp_half = kernel_supp / 2;
  float kdata[] = {0.0103339f, 0.207561f, 0.56421f, 0.207561f, 0.0103339f};

  Image image( "../images/im.pgm");

  Image image_conv( image.n_rows, image.n_cols);
  convSeparableHost( kdata, kernel_supp_half, image, image_conv);
  image_conv.write( "../images/im_conv_host.pgm");


  // check execution environment
  int device_handle = 0;
  int max_threads_per_block = 0;
  if( ! initDevice( device_handle, max_threads_per_block)) {
    return EXIT_FAILURE;
  }

  // initialize memory
  float* kernel_device = nullptr;
  float* image_device = nullptr;
  float* image_conv_device = nullptr;

  // allocate device memory
  checkErrorsCuda( cudaMalloc((void **) &kernel_device, sizeof(float) * kernel_supp));
  checkErrorsCuda( cudaMalloc((void **) &image_device, sizeof(float) * image.n_cols * image.n_rows));
  checkErrorsCuda( cudaMalloc((void **) &image_conv_device, sizeof(float) * image.n_cols * image.n_rows));

  // copy device memory
  checkErrorsCuda( cudaMemcpy( (void*) kernel_device, kdata,
                                sizeof(float) * kernel_supp,
                                cudaMemcpyHostToDevice ));
  checkErrorsCuda( cudaMemcpy( (void*) image_device, &(image.data[0]),
                                sizeof(float) * image.n_cols * image.n_rows,
                                cudaMemcpyHostToDevice ));

  // determine thread layout
  max_threads_per_block = 1024;
  const int max_threads_per_block_sqrt = 32;

  dim3 num_threads_per_block( 1, 1, 1);
  dim3 num_blocks( 1, 1, 1);
  
  //cut image in 32x32 blocks
  num_threads_per_block.x = max_threads_per_block_sqrt;
  num_threads_per_block.y = max_threads_per_block_sqrt;
  num_blocks.x = image.n_cols / max_threads_per_block_sqrt;
  num_blocks.y = image.n_rows / max_threads_per_block_sqrt;

  // run kernel
  assert( image.n_rows == image.n_cols);
  tpoint t_start = std::chrono::high_resolution_clock::now();
  convSeparable<kernel_supp_half><<< num_blocks , num_threads_per_block >>>( kernel_device, /*kernel_supp_half,*/
                                                           image_device, image_conv_device, image.n_rows);

  tpoint t_end = std::chrono::high_resolution_clock::now();
  double wall_clock = std::chrono::duration<double, std::milli>(t_end-t_start).count();
  std::cerr << "Execution time: " <<  wall_clock << " ms."<< std::endl;

  checkLastCudaError("Kernel execution failed");
  cudaDeviceSynchronize();

  // copy result back to host
  checkErrorsCuda( cudaMemcpy( &image_conv.data[0], image_conv_device,
                               sizeof(float) * image.n_cols * image.n_rows,
                               cudaMemcpyDeviceToHost ));
  // write result
  image_conv.write( "../images/im_conv_device.pgm");

  // clean up device memory
  checkErrorsCuda( cudaFree( kernel_device));
  checkErrorsCuda( cudaFree( image_device));
  checkErrorsCuda( cudaFree( image_conv_device));

  return EXIT_SUCCESS;
}
