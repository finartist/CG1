#include "spaceshipfactory.h"

// If spaceships.h would be not included, the SpaceShip's destructor would be unknown.
// This usually leads to a warning
#include "spaceships.h"


// TODO
/// Creates a SpaceShip from typename and string-parameter.
/// \param spaceShipTypeName	Registered SpaceShip type identifier.
/// \param parameter			String parameter for the creation of the SpaceShip.
/// \return shared_ptr to a new built spaceship or nullptr if the spaceShipTypeName was not registered.
std::shared_ptr<SpaceShip> SpaceShipFactory::buildSpaceShip(const std::string& spaceShipTypeName, const std::string& parameter) {
	auto got = m_createSpaceShipFuncRegister.find(spaceShipTypeName); //std::unordered_map<std::string, CreateSpaceShipFunc>::const_iterator
	if (got == m_createSpaceShipFuncRegister.end()) {
		return nullptr;
	}
	std::shared_ptr<SpaceShip> newSpaceShip = got->second(parameter);
	return newSpaceShip;
}

/// Makes a SpaceShip known to the factory.
/// \param spaceShipTypeName	SpaceShip type identifier to register.
/// \param createSpaceShipFunc	Function for creating the given SpaceShip.
/// \throw std::exception if the spaceShipTypeName already exists.
void SpaceShipFactory::registerSpaceShipType(const std::string& spaceShipTypeName, const CreateSpaceShipFunc& createSpaceShipFunc) {
	if (m_createSpaceShipFuncRegister.find(spaceShipTypeName) != m_createSpaceShipFuncRegister.end()) {
		throw std::exception();
	}
	m_createSpaceShipFuncRegister.insert({ spaceShipTypeName, createSpaceShipFunc });
}