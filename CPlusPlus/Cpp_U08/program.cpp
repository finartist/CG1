#include <iostream>
#include "Spacestuff.h"
#include <cassert>

// Task: write a program that handles interactions between planets, spaceships and deathstars
// Do this with unit tests.
// Step 1. Write Unit Tests for each class and functionality first. 
// Step 2. Write the classes and functions until each unit test passes.
//         - Also write error, debug and status output. 
//         - Each function should have error logging as well as "in-game" output. ("Deathstar was destroyed")
// Step 3. Test your work with the unit tests.
// 
// Note: Use exceptions and asserts where necessary / appropriate to test for programming and user errors.
// BONUS: write error logs to a seperate file, only display "normal" output in the console.

class TestSpaceShip
{
public:
	TestSpaceShip()
	{
		ships[0] = new SpaceShip();
		ships[1] = new SpaceShip(1, 2, 3, 6);
	}

	~TestSpaceShip()
	{
		delete ships[0];
		delete ships[1];
	}

	void test()
	{
		assert(ships[1]->getNumOfLaser() > 4);

		for (int i = 0; i < 20; ++i) {
			ships[0]->attack(*ships[1]);
		}

		assert(ships[1]->isDestroyed);

		ships[1]->attack(*ships[0]);
		std::cout << "ship 0 " << *ships[0] << std::endl;
		std::cout << "ship 1 " << *ships[1] << std::endl;
	}

private:
	SpaceShip* ships[2];
};

class TestDeathstar
{
public:
	TestDeathstar()
	{
		ships[0] = new Deathstar();
		ships[1] = new Deathstar(1, 2, 3, 6);
	}

	~TestDeathstar()
	{
		delete ships[0];
		delete ships[1];
	}

	void test()
	{
		assert(ships[1]->getNumOfLaser() > 4);

		for (int i = 0; i < 100; ++i) {
			ships[1]->attack(*ships[0]);
		}

		assert(ships[0]->isDestroyed);

		ships[0]->attack(*ships[1]);
		std::cout << "ship 0 " << *ships[0] << std::endl;
		std::cout << "ship 1 " << *ships[1] << std::endl;
	}

private:
	SpaceShip* ships[2];
};

class TestPlanet
{
public:
	TestPlanet()
	{
		p = new Planet();
		ships[0] = new SpaceShip();
		ships[1] = new Deathstar(1, 2, 3, 6);
	}

	~TestPlanet()
	{
		delete p;
		delete ships[0];
		delete ships[1];
	}

	void test()
	{
		ships[0]->attack(*p);
		assert(!p->isDestroyed);

		ships[1]->attack(*p);
		assert(p->isDestroyed);

		ships[0]->attack(*ships[1]);
		ships[0]->attack(*ships[1]);
		ships[0]->attack(*ships[1]);
		ships[0]->attack(*ships[1]);

		if (!ships[1]->isDestroyed)
		{
			ships[1]->attack(*ships[0]);
		}
	}

private:
	SpaceShip* ships[2];
	Planet* p;
};

void main()
{
	{
		TestSpaceShip test1 = TestSpaceShip();
		test1.test();
	}

	{
		TestDeathstar test1 = TestDeathstar();
		test1.test();
	}

	{
		TestPlanet test1 = TestPlanet();
		test1.test();
	}
}
