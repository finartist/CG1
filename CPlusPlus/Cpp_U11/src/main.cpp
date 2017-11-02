#include "spaceshipfactory.h"
#include "spaceships.h"
#include "fleet.h"
#include <string>

int main()
{
	// Register SpaceShips by using the SpaceShipFactory::getInstance().registerSpaceShipType with lambda functions.

	// TODO: Register TieFighter with SpaceShipFactory::getInstance().registerSpaceShipType
	// The string parameter contains the pilot.
	SpaceShipFactory::getInstance().registerSpaceShipType("TieFighter", [](const std::string& name) {
		std::shared_ptr<SpaceShip> ship = std::make_shared<SpaceShip>(TieFighter(name));
		return ship; });
	
	// TODO: Register DeathStar
	// The string parameter has to be interpreted as a float, determining its buildProgress
	SpaceShipFactory::getInstance().registerSpaceShipType("DeathStar", [](const std::string& progress) {
		std::shared_ptr<SpaceShip> ship = std::make_shared<SpaceShip>(DeathStar(std::stof(progress)));
		return ship; });

	// TODO: Register Destroyer
	// The string parameter has to be interpreted as an int, determining the number of lasers
	// Note that the appropriate parameterized instance of the "Destroyer"-class should be created.
	SpaceShipFactory::getInstance().registerSpaceShipType("Destroyer", [](const std::string& laser) {
		std::shared_ptr<SpaceShip> ship = std::make_shared<SpaceShip>(Destroyer(false, std::stoi(laser)));
		return ship; });
	
	// TODO: Register LargeDestroyer
	// The string parameter has to be interpreted as an int, determining the number of lasers.
	// Note that the appropriate parameterized instance of the "Destroyer"-class should be created.
	SpaceShipFactory::getInstance().registerSpaceShipType("LargeDestroyer", [](const std::string& laser) {
		std::shared_ptr<SpaceShip> ship = std::make_shared<SpaceShip>(Destroyer(true, std::stoi(laser)));
		return ship; });

	// Loads fleet from file
	Fleet imperialFleet("imperialships.txt");
	return 0;
}