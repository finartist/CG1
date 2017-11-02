#pragma once

#include <cassert>
#include <cstdlib>
#include <memory>

/// \brief A dynamic array which uses local space as long as it isn't too large.
/// \details The array itself contains space for n elements. As long as the
///		number of elements is smaller than this local capacity there is no
///		additional memory block somewhere else. Data and meta information are
///		both part of the same block. This increases speed as long as we are
///		dealing with small arrays (<n). E.g. strings
///
///		In case there is more data the array behaves as std::vector.
///
///		As usual copying arrays is expensive.
template<typename T, unsigned n = 8>
class HybridArray
{
public:
	typedef T ElemType;

	/// \brief Standard: create this array with capacity n and size 0.
	/// \details m_data is set to the local storage.
	// TODO: Write standard CTor which sets m_data, m_capacity and m_size
	HybridArray();

	/// \brief Create a dynamic array with preallocated space. Probably
	///		this disables the advantage of 'hybrid':
	///	\param [in] _capacity Minimum capacity which should be allocated.
	///		The allocated capacity must be ´max(_capacity, n)´. So there is
	///		never less than the already existing internal memory block.
	// TODO: Write custom CTor which sets m_data, m_capacity and m_size.
	//		You might need to allocate memory with malloc!
	HybridArray(unsigned _capacity);

	/// \brief Copy construction (deep).
	// TODO: Write copy constructor. Use placement new -new (already allocated memory) Type();-  with copy CTor of ElemType
	//		to create copies of the data (not =).
	//		Reason: Any implementation of = must free the old stuff on the left
	//		side before assigning the new one. But during construction there is
	//		nothing to be deleted (left side contains uninitialized memory)!
	HybridArray(const HybridArray<T, n>& _other);

	/// \brief Calls destructor for all contained elements.
	// TODO: Write destructor.
	~HybridArray();

	/// \brief Deep copying assignment
	// TODO: Write assignment operator for this array type. Keep in mind: delete
	//		the old content first (otherwise memory leaks)
	HybridArray<ElemType,n>& operator = (const HybridArray<T,n>& _other);

	/// \brief Write-array access.
	// TODO: Implement [] operator with write access (non const)
	inline T& operator[](int i)
	{
		return m_data[i];
	}

	/// \brief Read-array access.
	// TODO: Implement [] operator with read access (const)
	inline const T& operator[](int i) const
	{
		return m_data[i];
	}

	/// \brief Enlarge or prune the memory.
	/// \details This function is already implemented. You can have a look
	///		to understand the array and to see how std::swap can be used.
	/// \param [in] _capacity New capacity/size. If _capacity is below n
	///		the new capacity is still n.
	///		
	///		A value smaller than size() causes a prune: capacity = size();
	void reserve(unsigned _capacity = 0);

	/// \brief Insert an element copy at the end of the array.
	/// \details This might cause a resize with costs O(n).
	// TODO: Implement (use placement new to copy the element to the array,
	//	for a reason see Copy-CTor above).
	const ElemType& pushBack(const ElemType& _element);

	/// \brief Delete the last element
	// TODO: Implement
	void popBack();

	unsigned size() const		{ return m_size; }
	unsigned capacity() const	{ return m_capacity; }

	/// \brief Access first element
	ElemType& front()				{ assert(m_size>0); return *m_data; }
	const ElemType& front() const	{ assert(m_size>0); return *m_data; }

	/// \brief Access last element
	ElemType& back()				{ assert(m_size>0); return m_data[m_size-1]; }
	const ElemType& back() const	{ assert(m_size>0); return m_data[m_size-1]; }
protected:
	ElemType* m_data;		///< Pointer to array memory block. Might be on stack or heap.
	unsigned m_capacity;	///< Maximum number of elements
	unsigned m_size;		///< Current number of elements

	unsigned char m_localStorage[sizeof(T)*n];	///< The local storage on stack or in object heap space.
};






// ********************************************************************* //
//  HybridArray Implementation											 //
// ********************************************************************* //
template<typename T, unsigned n>
HybridArray<T,n>::HybridArray() : m_capacity(n), m_size(0) {
	m_data = (T*) m_localStorage;
};

template<typename T, unsigned n>
HybridArray<T, n>::HybridArray(unsigned _capacity) : m_size(0) {
	if (_capacity > n) {
		m_data = (T*)malloc(_capacity * sizeof(T));
	}
	else {
		m_data = (T*)m_localStorage;
	}
	m_capacity = n < _capacity ? m_capacity : n;
};

template<typename T, unsigned n>
HybridArray<T, n>::HybridArray(const HybridArray<T, n>& _other):
	HybridArray()
{
	reserve(_other.m_capacity);
	for (unsigned int i = 0; i < _other.m_size; ++i) {
		new ((void*)&m_data[i]) T(_other.m_data[i]);
	}
	m_size = _other.m_size;
	m_capacity = _other.m_capacity;
};

template<typename T, unsigned n>
HybridArray<T, n>::~HybridArray() {
	for (int i = 0; i < m_size; ++i)
		m_data[i].~T();

	if (m_capacity > n)
		free(m_data);
};

template<typename T, unsigned n>
HybridArray<T, n>& HybridArray<T, n>::operator = (const HybridArray<T, n>& _other) {
	
	for (unsigned int i = 0; i < m_size; ++i) {
		m_data[i].~T();
	}
	reserve(_other.m_capacity);
	for (unsigned int i = 0; i < _other.m_size; ++i) {
		new ((void*)&(m_data[i])) T(_other.m_data[i]);
	}
	m_size = _other.m_size;
	m_capacity = _other.m_capacity;
	return *this;
}

template<typename T, unsigned n>
const T& HybridArray<T, n>::pushBack(const ElemType& _element) {
	if (m_size == m_capacity) {
		reserve(m_capacity * 2);
	}
	T* elem = new ((void*)&m_data[m_size]) T(_element);
	++m_size;
	return *elem;
}

template<typename T, unsigned n>
void HybridArray<T, n>::popBack() {
	m_data[--m_size].~T();
}

// ********************************************************************* //
template<typename T, unsigned n>
void HybridArray<T,n>::reserve(unsigned _capacity)
{
	// Resizing without change. Bad Performance!
	assert(m_capacity != _capacity);

	// Prune as much as possible - do not delete data
	if( _capacity <= m_size ) _capacity = m_size;

	unsigned oldCapacity = m_capacity;
	m_capacity = _capacity < n ? n : _capacity;
	// If both old and new capacity are <= n nothing happens otherwise
	// a realloc or copy is necessary
	if( m_capacity > n || oldCapacity > n )
	{
		ElemType* oldData = m_data;

		// Determine target memory
		if( m_capacity <= n )
			m_data = reinterpret_cast<ElemType*>(m_localStorage);
		else
			m_data = (ElemType*)malloc( m_capacity * sizeof(ElemType) );

		// Now keep the old data
		for( unsigned i=0; i<m_size; ++i )
			std::swap(m_data[i], oldData[i]);

		if( oldCapacity > n )
			free(oldData);
	}
}
