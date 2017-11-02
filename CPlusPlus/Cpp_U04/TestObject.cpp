#include "TestObject.h"

TestObject::TestObject()
{
    m_NumberOfStrings = 10;
    generateStrings();
}


TestObject::TestObject(int numberOfStrings)
{
    m_NumberOfStrings = numberOfStrings;
    generateStrings();
}


TestObject::~TestObject()
{
	// got to be array delete[] not single delete
    delete[] m_ArrayOfStrings;
}

void TestObject::generateStrings()
{
    m_ArrayOfStrings = new std::string[m_NumberOfStrings];

    for (int i = 0; i < m_NumberOfStrings; i++)
    {
        std::string& currentString = m_ArrayOfStrings[i];
        
        int numberOfChars = rand() % 30 + 1;
        currentString.resize(numberOfChars);
        for (auto& c : currentString)
        {
            c = RandomChar::getRandomChar();
        }
     }
}

void TestObject::swapStrings(std::string& first, std::string& second)
{
	// use std::swap to swap references
	std::swap(first, second);

}


void TestObject::printAllStrings() const
{
    for (int i = 0; i < m_NumberOfStrings; i++)
    {
        std::cout << m_ArrayOfStrings[i];
    }
    std::cout << std::endl;
}


// return the number of elements
const int& TestObject::elementCount() const
{
    return m_NumberOfStrings;
}

void TestObject::reverseArray()
{
	// BONUS
}

std::string& TestObject::readAccess(const int index) const{
	return  m_ArrayOfStrings[index];
}