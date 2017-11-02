#include "dynamicobjectlist.hpp"

DynamicObjectList::DynamicObjectList() : m_count(0), m_capacity(0) {
	reserve(5);
}

DynamicObjectList::~DynamicObjectList() {
	// first delete objects in list
	for (unsigned int i = 0; i < m_count; ++i) {
		delete m_list[i];
	}
	// then delete list array
	delete[] m_list;
}

void DynamicObjectList::reserve(const unsigned int capacity) {
	// if capacity is already greater or equal the value, nothing happens
	if (m_capacity >= capacity) {}
	// if we have no object yet(like when we initialize a list) or if we have an empty list and want to rise capacity 
	else if (m_count == 0) {
		// führt zu böser Exception, weil m_list nicht initialisiert am Anfang
		//delete[] m_list;
		m_list = new Object*[capacity];
		m_capacity = capacity;
	}
	// else we copy the object pointer to a new pointer array, delete the pointer of mylist and replace it by the bigger copy
	else {
		Object** tempCopy = new Object*[capacity];
		for (unsigned int i = 0; i < m_count; ++i) {
			tempCopy[i] = m_list[i];
		}
		delete[] m_list;
		m_list = tempCopy;
		m_capacity = capacity;
	}
	
}

 Object* DynamicObjectList::createObject_back(char* name) {
	//if we do not have enough capacity, we double it
	if (m_count == m_capacity) {
		reserve(m_capacity * 2);
	}
	// then add a new Object-Pointer to list
	// dafür include object.h im Header, weil sonst kennt er keinen Konstruktor für Object
	Object* newOb = new Object(name);
	m_list[m_count] = newOb;
	// nicht vergessen! sonst Exception, beim Löschen (versucht immer auf gleiche Stelle zuzugreifen, auch wenn schon gelöscht)
	++m_count;
	return newOb;
}

void DynamicObjectList::destroyObject(const unsigned int position){
	// if there is no object at position, nothing happens
	if (position > m_count - 1) return;
	// else we delete object at position and move pointer after position one to the left
	else {
		delete m_list[position];
		for (unsigned int i = 0; i < m_count - position; ++i) {
			// if the last element does not have a free position following, set the last position a nullprt 
			// (no pointer lost because it has been shifted to the left before)
			if (position + i + 1 == m_capacity) { 
				m_list[position + i] = nullptr;

			}
			else {
				m_list[position + i] = m_list[position + i + 1];
			}
		}
	}
	// nicht vergessen! sonst Exception, beim Löschen des nächsten (versucht wieder auf gleiche Stelle zuzugreifen, aber da ist nichts mehr ":/ )
	--m_count;
}

Object* DynamicObjectList::getAt(const unsigned int position) const{
	if (position >= m_count || position < 0) { return nullptr; }
	else { return m_list[position]; }
}