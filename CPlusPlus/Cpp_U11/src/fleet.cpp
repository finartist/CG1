#include "fleet.h"
#include "spaceshipfactory.h"

#include <fstream>
#include <sstream>
#include <assert.h>

Fleet::Fleet(const std::string& fleetFilename)
{
	// TODO:
	// * Open textfile in "fleetFilename"
	std::ifstream stream(fleetFilename);
	std::string line = "";
	// * Parse line by line:
	while (std::getline(stream, line)) {
		// ** Separate in ship name and parameter at the first ' '
		std::istringstream curline(line);
		std::string shipname;
		std::getline(curline, shipname, ' ');
		std::string parameter;
		std::getline(curline, parameter);
		// ** call SpaceShipFactory::getInstance().buildSpaceShip
		std::shared_ptr<SpaceShip> oneOfFleet = SpaceShipFactory::getInstance().buildSpaceShip(shipname, parameter);
		// ** push into m_spaceShips if a new ship was successfully created
		if (oneOfFleet) {
			m_spaceShips.push_back(oneOfFleet);
		}
	}
}