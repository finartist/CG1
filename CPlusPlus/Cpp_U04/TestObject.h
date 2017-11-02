// This also has a few bugs and things missing.
// TODO: Identify and fix errors / bugs / mistakes

// TODO: Identify functions and return values that should be const
// also see instructions in programm.cpp, if you haven't already

//#include "TestObject.h"
#include <iostream>
#include <string>
#include <cstdlib>
#include "RandomChar.h"

/// contains an array of strings of random chars
class TestObject
{
public:
    TestObject();
    TestObject(int numberOfStrings);
    ~TestObject();

    // TODO: swap elements using references.
    void swapStrings(std::string& first, std::string& second);

    void printAllStrings() const;

    /// return the number of strings / elements
    const int& elementCount() const;

    // BONUS: Reverse the array. // Do you use pointers or references? what is the difference here?
    void reverseArray();

    //TODO return reference to string at index. (read only)
    std::string& readAccess(const int index) const;

private:
    void generateStrings();
    std::string* m_ArrayOfStrings;
    int m_NumberOfStrings;
};