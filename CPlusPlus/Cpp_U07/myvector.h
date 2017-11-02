#pragma once

#include <sstream>
#include <stdexcept>


/// \brief Mixin for calculating the length (euclidian norm / distance) of a vector
// BONUS: Implement a mixin that calculates the length of a vector.
// Change the vector template to include this mixin.

	/// \brief Template class for (math) vectors of variable length and type.
	/// Works with all basic number types.
	template<unsigned int Size, typename Element = float>
	class Vector
	{
	public:

		// As always use const wherever meaningful!
		// This time you have to think about meaningful function signatures yourself.
		// However, the given documentation should make it clearly what these signatures should look like.
		// If your signature does not work with the given test-code but you're absolutely sure
		// that your signature fits the requirements, just alter the test-code accordingly! ;)


		/// Constructor that initializes all elements with zero.
		// Todo: Implement a constructor without any arguments that does exactly that.
		Vector<Size, Element>()
		{
			for (int i = 0; i < Size; ++i) {
				elements[i] = 0;
			}
		};

		/// Constructor that copies the vector
		// Todo: Implement a copy-constructor.
		Vector<Size, Element>(const Vector<Size, Element>& otherVector)
		{
			for (int i = 0; i < otherVector.SIZE; ++i) {
				elements[i] = otherVector.elements[i];
			}
		};

		/// Constructor that copies a vector of an arbitrary type with the same size.
		/// The type must be convertible by a static_cast to Element.
		// Todo: Implement a constructor that takes a reference to a vector with
		// an arbitrary element type. The right vector must have the same size - only
		// the element type differs.
		// Hint: Take the element type of the right vector as additional template argument
		// for the function.
		template <typename T>
		Vector<Size, Element>(const Vector<Size, T>& otherVector)
		{
			if (otherVector.SIZE == Size) {
				for (int i = 0; i < otherVector.SIZE; ++i) {
					elements[i] = static_cast<Element> (otherVector[i]);
				}
			}
		};

		/// \brief Constructor that initializes the vector from a string.
		/// The string should contain values, separated by spaces. Uses stringstream for parsing.
		/// Will set all other elements to zero.
		// Todo: Implement a construction from std::string that fulfills the given documentation.
		Vector<Size, Element>(std::string string)
		{
			std::stringstream ss(string);
			std::string el;
			for (int i = 0; i < Size; ++i) {
				if (ss >> el)
				{
					elements[i] = static_cast<Element> (std::stol(el));
				}
				else
				{
					elements[i] = 0;
				}
			}
		};

		/// \brief Returns a string representation of the vector.
		// Todo: Implement a casting operator overload to std::string.
		inline operator std::string() const
		{
			std::string string;
			for (int i = 0; i < this->SIZE; ++i) {
				if (i < this->SIZE - 1)
				{
					string += std::to_string(elements[i]) + " ";
				}
				else
				{
					string += std::to_string(elements[i]);
				}
			}
			return string;
		};

		/// \brief Read access to an element.
		/// Checks for errors in debug mode - perform an error report of your choice
		// Todo: Implement a [] operator overload for reading!
	// read access means a const function returning const reference
		inline const Element& operator[] (int i) const
		{
			return this->elements[i];
		};

		/// \brief Write access to an element.
		/// Checks for errors in debug mode - perform an error report of your choice
		// Todo: Implement a [] operator overload for read/write access!
		inline Element& operator[] (int i)
		{
			return this->elements[i];
		};

		/// \brief Adds to vectors of the same size and type element-wise.
		// Todo: Implement a + operator overload that takes a vector of same type and size.

		inline Vector<Size, Element> operator + (const Vector<Size, Element>& rhs)
		{
			Vector<Size, Element> v;
			for (int i = 0; i < Size; ++i) {
				v.elements[i] = this->elements[i] + rhs.elements[i];
			}
			return v;
		};

		/// \brief Simple template programming trick to make size accessible from outside.
		enum MetaInfo
		{
			SIZE = Size
		};

	private:
		/// Intern data representation.
		Element elements[Size];
	};