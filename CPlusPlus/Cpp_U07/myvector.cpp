#include "myvector.h"

template<unsigned int Size, typename Element = float>

/// Constructor that initializes all elements with zero.
// Todo: Implement a constructor without any arguments that does exactly that.
myvector::Vector<Size, Element>()
{

}

	/// Constructor that copies the vector
	// Todo: Implement a copy-constructor.
myvector::Vector<Size, Element>(const Vector<Size, Element>& otherVector)
{

}

	/// Constructor that copies a vector of an arbitrary type with the same size.
	/// The type must be convertible by a static_cast to Element.
	// Todo: Implement a constructor that takes a reference to a vector with
	// an arbitrary element type. The right vector must have the same size - only
	// the element type differs.
	// Hint: Take the element type of the right vector as additional template argument
	// for the function.
myvector::Vector<Size, Element>(const Vector<Size, Element>& otherVector)
{

}

	/// \brief Constructor that initializes the vector from a string.
	/// The string should contain values, separated by spaces. Uses stringstream for parsing.
	/// Will set all other elements to zero.
	// Todo: Implement a construction from std::string that fulfills the given documentation.
myvector::Vector<Size, Element>(std::string string)
{

}

	/// \brief Returns a string representation of the vector.
	// Todo: Implement a casting operator overload to std::string.
myvector::operator std::string()
{

}

	/// \brief Read access to an element.
	/// Checks for errors in debug mode - perform an error report of your choice
	// Todo: Implement a [] operator overload for reading!
const Element& myvector::operator[] (int i)
{

}

	/// \brief Write access to an element.
	/// Checks for errors in debug mode - perform an error report of your choice
	// Todo: Implement a [] operator overload for read/write access!
Element& myvector::operator[] (int i)
{

}

/// \brief Adds to vectors of the same size and type element-wise.
// Todo: Implement a + operator overload that takes a vector of same type and size.
Vector<Size, Element> myvector::operator + (const Vector<Size, Element>& lhs, const Vector<Size, Element>& rhs)
{

}