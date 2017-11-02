#pragma once
#include <iostream>
#include <time.h>
#include <stdlib.h>

// Classes: Target, Planet, Spaceship and Deathstar. 
// Planet, Spaceship and Deathstar are all be targets. 

// Target.
// Every Target has a position and name. 
// Each target can be attacked and can explode
// Exploding cannot be triggered from outside the class. (only through receiving damage)
// Should have a stream out operator. 
// Can be purely virtual (abstract).

class Target {
public:
	Target() 
	{
		position[0] = 0;
		position[1] = 0;
		position[2] = 0;
	};
	Target(int x, int y, int z)
	{
		position[0] = x;
		position[1] = y;
		position[2] = z;
	}
	virtual ~Target() {};

	virtual void takeDamage(std::string attacktype, int attackstrenght) = 0;
	
	int position[3];
	std::string type;
	bool isDestroyed = false;

protected:
	virtual void explode() = 0;
	/*Hier sollte explode dann nicht abstrakt sein sondern:
	void explode()
	{
		std::cout << this->type.c_str() << " at " << *this << "was destroyed." << std::endl;
		isDestroyed = true;
	}

	und explode aus den anderen Klassen entfernt. Da kam dann immer das Problem, dass Binärer Operator << ist nicht definiert 
	für Typ Target oder konnte nicht konvertiert werden oder so ähnlich.

	*/
};

std::ostream& operator << (std::ostream& stream, const Target& t) {
	return stream << "position: [" << t.position[0] << ", " << t.position[1] << ", " << t.position[2] << "], type: " <<  t.type.c_str() <<std::endl;
}

// Planet
// Inherits from Target
// Can only be destroyed by Deathstars.
// Planets cannot attack

class Planet : public Target {
public:
	Planet() : Target()
	{
		type = "Planet";
	}

	Planet(int x, int y, int z) : Target(x, y, z)
	{
		type = "Planet";
	}

	virtual ~Planet() 
	{
		std::cout << "This planet does not longer exist" << std::endl;
	};

	virtual void takeDamage(const std::string attacktype, int attackstrenght)
	{
		if (attacktype == "Deathstar" && !isDestroyed)
		{
			explode();
		}
		else
		{
			std::cout << "Nothing happened" << std::endl;
		}
	}

private:
	virtual void explode()
	{
		std::cout << "Planet at " << *this << "was destroyed." << std::endl;
		isDestroyed = true;
	}
};

// Spaceship
// Inherits from Target
// Has a number of lasers
// Has shields (percentage)
// Can attack other targets
// When attacked, shields are damaged. When shields <= 0, it explodes.

class SpaceShip : public virtual Target {

public:
	SpaceShip() : Target(), m_numOfLaser(4), m_shield(100)
	{
		type = "SpaceShip";
		m_attackstrength = m_numOfLaser * 2;
	}

	SpaceShip(int x, int y, int z, int laser) : Target(x, y, z), m_numOfLaser(laser), m_shield(100)
	{
		type = "SpaceShip";
		m_attackstrength = m_numOfLaser * 2;
	}

	virtual ~SpaceShip()
	{
		std::cout << "This SpaceShip does not longer exist" << std::endl;
	};

	virtual void takeDamage(const std::string attacktype, int attackstrenght)
	{
		if (!isDestroyed)
		{
			m_shield -= attackstrenght;
			if (m_shield <= 0)
			{
				explode();
			}
			else
			{
				std::cout << "Spaceship was damaged" << std::endl;
			}
		}
		else
		{
			std::cout << "SpaceShip at " << *this << "is already destroyed." << std::endl;
		}
	}

	void attack(Target& target) {
		if(!isDestroyed)
		{
			target.takeDamage(this->type, this->m_attackstrength);
		}
		else
		{
			std::cout << "If destroyed, you cannot attack!" << std::endl;
		}
	}

	const int getNumOfLaser() const
	{
		return this->m_numOfLaser;
	}

protected:
	int m_numOfLaser;
	int m_shield;
	int m_attackstrength;

private:
	virtual void explode()
	{
		std::cout << "SpaceShip at " << *this << "was destroyed." << std::endl;
		isDestroyed = true;
	}
};

// Deathstar
// Inherits from Spaceship
// Has far higher shields
// Can be damaged by spaceships with a 25% chance of exploding directly (without needing to get the shields to zero).
//        (- the reactor core can be hit through the exhaust vent. ;) )

class Deathstar : public SpaceShip {
public:
	Deathstar() : Target() 
	{
		type = "Deathstar";
		m_numOfLaser = 4;
		m_shield = 1000;
		m_attackstrength = m_numOfLaser * 10;
	}

	Deathstar(int x, int y, int z, int laser) : Target(x, y, z)
	{
		type = "Deathstar";
		m_numOfLaser = laser;
		m_shield = 1000;
		m_attackstrength = m_numOfLaser * 10;
	}

	virtual ~Deathstar()
	{
		std::cout << "This Deathstar does not longer exist" << std::endl;
	};

	virtual void takeDamage(const std::string attacktype, int attackstrenght)
	{
		m_shield -= attackstrenght;
		if (!isDestroyed)
		{
			srand(time(NULL));
			int r = rand();

			if (m_shield <= 0 || r % 4 == 0)
			{
				explode();
			}
			else
			{
				std::cout << "Deathstar is damaged" << std::endl;
			}
		}
		else
		{
			std::cout << "Deathstar at " << *this << "is already destroyed." << std::endl;
		}
	}

private:

	virtual void explode()
	{
		std::cout << "Deathstar at " << *this << "was destroyed." << std::endl;
		isDestroyed = true;
	}

};