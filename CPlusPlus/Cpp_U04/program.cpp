
#include <iostream>
#include <time.h>
#include "TestObject.h"

// ### Exercise 4 - References ###
// # Firstly, some of the code has questions to it, please answer these in your own words. (not necessarily in code or comments. ;) )
// # Secondly, please fix any errors you see, explain your solution in your own words.
// # 
// # Bonus 1: Think of a good use of pointers, then try to implement it with references. Document any problems, errors and solutions.
// # Bonus 2: Make everything const correct.


// This codes has quite a few (intentional) errors in it
// TODO: debug and fix the errors.

// Should return the reference to a sum of two ints
// TODO: rewrite this as a template (without the bug)
template <typename T>
T sumTheElements(T a, T b)
{
	// make it a reference, otherwise the reference points to heap, where sum is already distroyed (because of function scope)
    T sum = a + b;
    return sum;
}

void main()
{
    // using a bit of random here.
    srand(time(nullptr)); //initialize random seed based on time

    // Where will this object live? Is this a good choice? Why or why not?
	 // This Object will be on the Stack. Not so good, because we have a pointer to it later
	 // its not so bad here, but normally it is bad to have a pointer to something on the stack 
	 // because if the scope ends and the pointer continues to exist, it can point to something useless
    TestObject anObject = TestObject(30);

    // Name differences between pointers and references
	 // Pointer can be changed, references not after initialization. pointer can be null, refer. not. References can be used like the object and need no dereference
    TestObject* anotherObject = new TestObject(2);

    // When should we use pointers, references and values? why?
	 // pointers for optional parameters
	 // references for any none-elemental datatypes
	 // values for small (32/64Bit) datatype and no change

	//reference is initialized with variable name, not adress
    TestObject& referenceToAnObject = anObject;

	// same here, so we have to dereference the pointer "anotherObject" first
    TestObject& referenceToAnotherObject = *anotherObject;

	// pointer is initialized with adress, so we have to get ADRESS from "anObject"
    TestObject* pointerToAnObject = &anObject;

	// same here, although referenceToAnotherObject is a reference(basically a adress too) 
	// it is used like the object itself, so we need to GET the ADRESS here too
    TestObject* pointerToAnotherObject = &referenceToAnotherObject;
    

    // Testing if the correct strings are being printed.
    referenceToAnotherObject.printAllStrings();
    std::cout << "The Following should be the same as above: " << std::endl;
    pointerToAnotherObject->printAllStrings();

    std::cout << std::endl << "total number of strings:" << std::endl;
   
    int& TotalElementNumber = sumTheElements(referenceToAnObject.elementCount(), referenceToAnotherObject.elementCount());
    std::cout << TotalElementNumber << std::endl;
    std::cout << "Do calculations in another scope and display the same value again inside that scope: " << std::endl; 
    { 
        //this isn't really doing anything, just for testing purposes
        int ignoreMe = 20; 
        ignoreMe *= TotalElementNumber;
        std::cout << TotalElementNumber << std::endl;
    }

    std::cout << "And again outside the scope: " << std::endl;
    std::cout << TotalElementNumber << std::endl;


    delete anotherObject;
	// these pointers have not been initialized with new, so cannot be deleted
    //delete pointerToAnObject;
    //delete pointerToAnotherObject;


}

