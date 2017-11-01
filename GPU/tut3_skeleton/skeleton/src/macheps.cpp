/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : tutorial 3
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: machine precision
///
////////////////////////////////////////////////////////////////////////////////////////////////////

// includes, system
#include <iostream>
#include <iomanip>
#include <limits>

////////////////////////////////////////////////////////////////////////////////////////////////////
// return machine precision for the type T
////////////////////////////////////////////////////////////////////////////////////////////////////
template< typename T >
T
macheps() {

  T eps = 1.0;

  return eps;
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// program entry point
////////////////////////////////////////////////////////////////////////////////////////////////////
int
main( int /*argc*/, char** /*argv*/ ) {

  std::cout << std::scientific << std::setprecision( 8);
  std::cout << "eps = " << macheps<float>() << std::endl;
  std::cout << "eps = " << std::numeric_limits<float>::epsilon() << std::endl;

  std::cout << std::scientific << std::setprecision( 17);
  std::cout << "eps = " << macheps<double>() << std::endl;
  std::cout << "eps = " << std::numeric_limits<double>::epsilon() << std::endl;

  return EXIT_SUCCESS;
}