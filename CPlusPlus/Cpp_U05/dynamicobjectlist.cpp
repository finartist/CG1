#include "dynamicobjectlist.hpp"
#include "object.h"
#include <algorithm>
DynamicObjectList::DynamicObjectList() :
m_count(0),
m_capacity(0),
m_list(nullptr)
{
}

//Copy-Constructor
DynamicObjectList::DynamicObjectList(const DynamicObjectList& otherList) :
	m_capacity(0), m_count(0)
	//capacity und count erst später setzen! sonst macht reserve nichts, weil capacity = capacity, die er reservieren will
{
	m_list = new Object*[0];
	// Erzeuge eigenen Speicherblock
	reserve(otherList.m_capacity);
	// Kopiere Teilobjekte rekursiv
	for (unsigned int i = 0; i < otherList.m_count; ++i) {
		Object* o = new Object(otherList.m_list[i]->GetName());
		m_list[i] = o;
	}
	m_count = otherList.m_count;
}

DynamicObjectList::~DynamicObjectList()
{
	std::cout << "Destroy list with " << m_count << " elements." << std::endl;
	// first delete objects in list
	for (unsigned int i = 0; i < m_count; ++i) {
		destroyObject(i);
		--i;
	}
	// then delete list array
	delete[] m_list;
}

void DynamicObjectList::reserve(unsigned int capacity)
{
  // Check if anything should happen.
  if(m_capacity >= capacity)
    return;

  // Create new object array with the given capacity.
  Object** newArray = new Object*[capacity];

  // Moves old list contents to new list.
  for(unsigned int i = 0; i < m_count; ++i)
  {
    newArray[i] = m_list[i];
  }

  // Optional: Set all other pointer to nullptr, just for easier debugging.
  for(unsigned int i = m_count; i < capacity; ++i)
  {
    newArray[i] = nullptr;
  }

  // delete old array!
  delete[] m_list;

  // Save new data.
  m_list = newArray;
  m_capacity = capacity;
}

///Shrink allocated memory to max 5*m_count, do not shrink under 2*m_count, only half or double m_capacity
///e.g. [1,2,3,4] with m_capacity=16 has to be shrunk to [1,2,3] with m_capacity=8 if the last element is removed
/// shrink is called when an object is deleted from the list
void DynamicObjectList::shrink() {
	// Check if anything should happen.
	if (m_capacity < m_count*5 || m_capacity/2 < 2*m_count)
		return;

	// Create new object array with the given capacity.
	Object** newArray = new Object*[m_capacity/2];

	// Moves old list contents to new list.
	for (unsigned int i = 0; i < m_count; ++i)
	{
		newArray[i] = m_list[i];
	}

	// Optional: Set all other pointer to nullptr, just for easier debugging.
	for (unsigned int i = m_count; i < m_capacity/2; ++i)
	{
		newArray[i] = nullptr;
	}

	// delete old array!
	delete[] m_list;

	// Save new data.
	m_list = newArray;
	m_capacity = m_capacity/2;
}

Object* DynamicObjectList::createObject_back(char* name)
{
	// Ensure capacity.
	reserve((m_count + 1) * 2);

	// Add new object.
	m_list[m_count] = new Object(name);

	return m_list[m_count++];
}

void DynamicObjectList::sort()
{
	for (unsigned int j = 1; j < m_count-2; j++)    // Start with 1 (not 0)
	{
		unsigned int i = 0;
		Object *tmp = m_list[j];
		for (i = j - 1; (i < m_count) && (*m_list[i] < *tmp); --i)   // Smaller values move up
		{
			m_list[i + 1] = m_list[i];
		}
		m_list[i + 1] = tmp;    //Put key into its proper location
	}
}

Object* DynamicObjectList::push_back(Object *a)
{
	if (a == nullptr)
		return a;
	// Ensure capacity.
	reserve((m_count + 1) * 2);

	// Add new object.
	m_list[m_count] = a;

	return m_list[m_count++];
}

Object* DynamicObjectList::createObject_front(char* name)
{
  // Ensure capacity.
  reserve((m_count + 1) * 2);

  // Move all other objects.
  for (unsigned int i = m_count; i >= 1; --i)
  {
    m_list[i] = m_list[i - 1];
  }

  // Add new object.
  m_list[0] = new Object(name);
  ++m_count;

  return m_list[0];
}

void DynamicObjectList::destroyObject(unsigned int position)
{
  // Does the element exist?
  if(m_count <= position)
  {
    return;
  }

  // Destroy the object at the given position.
  delete m_list[position];

  // Now we have less objects!
  --m_count;

  // Move all other objects.
  for (unsigned int i = position; i < m_count; ++i)
  {
    m_list[i] = m_list[i + 1];
  }
  shrink();
  // Optional: Set former back to nullptr for easier debugging.
  m_list[m_count] = nullptr;
}

Object* DynamicObjectList::getAt(unsigned int position)
{
	// Does the element exist?
	if (m_count <= position)
	{
		return nullptr;
	}

	return m_list[position];
}
/* todo*/
Object* DynamicObjectList::getAt(const unsigned int position) const
{
	if (m_count <= position)
	{
		return nullptr;
	}

	return m_list[position];
}

// = Operator (Zuweisungsoperator)
DynamicObjectList& DynamicObjectList:: operator= (const DynamicObjectList& otherList)
{
	// if want to assign this object to this
	if (this == &otherList) return *this;
	// Zuerst eigenen alten Speicher löschen
	delete[] m_list;
	// Dann Objects kopieren
	// erst alles auf null setzen -> sonst Problem bei reserve (wie in CopyConstructor)
	m_capacity = 0;
	m_count = 0;
	reserve(otherList.m_capacity);
	for (unsigned int i = 0; i < otherList.m_count; ++i) {
		Object* o = new Object(otherList.m_list[i]->GetName());
		m_list[i] = o;
	}
	return *this;
}

// neue Objecte beim push back! sonst ein Object in mehreren Listen -> problem beim Löschen
//+ Operator
DynamicObjectList DynamicObjectList::operator+ (const Object& o) {
	DynamicObjectList temp = DynamicObjectList(*this);
	reserve((temp.m_count + 1) * 2);
	temp.push_back(new Object(o));
	return temp;
}

//+= Operator
///push_back element
DynamicObjectList& DynamicObjectList::operator+=(const Object& o) {
	this->push_back(new Object(o));
	return *this;
}

//+ Operator
DynamicObjectList DynamicObjectList::operator+ (Object& o) {
	DynamicObjectList temp = DynamicObjectList(*this);
	temp.push_back(new Object(o));
	return temp;
}

// += Operator
///push_back element
DynamicObjectList& DynamicObjectList::operator+= (Object& o) {
	this->push_back(new Object(o));
	return *this;
}

// |= Operator
///merge lists, use multiset union semantic.
///e.g. [1,2,3,4,5,6]+=[2,5,9,9] returns [1,2,3,4,5,6,2,5,9,9]
DynamicObjectList& DynamicObjectList::operator|=(const DynamicObjectList& o) {
	for (unsigned int i = 0; i < o.m_count; ++i) {
		// nicht push back, sonst ein Object in mehreren Listen -> Problem beim Löschen
		this->createObject_back(const_cast<char*>(o.getAt(i)->GetName()));
	}
	return *this;
}

//&= Operator
///intersect lists
///e.g. [1,2,3,4,5,6,9,21]&=[2,5,9,9] returns [2,5,9,9]
DynamicObjectList& DynamicObjectList::operator&= (const DynamicObjectList& o) {
	bool contains = false;
	for (unsigned int i = 0; i < this->m_count; ++i) {
		
		contains = false;
		for (unsigned int j = 0; j < o.m_count; ++j) {
			if (strcmp(this->getAt(i)->GetName(), o.getAt(j)->GetName()) == 0) {
				contains = true;
				break;
			}
		}
		if (contains == false && !(i < 0)) {
			this->destroyObject(i);
			--i;
		}
	}
	return *this;
}