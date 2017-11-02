#pragma once

#include <memory>
#include <string>
#include <functional>
#include <unordered_map>

// predeclaration saves us from including the spaceships.h
class SpaceShip;

/// \brief Factory for creating SpaceShip by using a runtime dictionary
class SpaceShipFactory
{
public:
	typedef std::function<std::shared_ptr<SpaceShip>(const std::string& parameter)> CreateSpaceShipFunc;

	/// \brief returns singleton instance
	static SpaceShipFactory& getInstance()
	{
		static SpaceShipFactory instance;
		return instance;
	}

	/// Creates a SpaceShip from typename and string-meter.
	/// \param spaceShipTypeName	Registered SpaceShip type identifier.
	/// \param parameter			String parameter for the creation of the SpaceShip.
	/// \return shared_ptr to a new built spaceship or nullptr if the spaceShipTypeName was not registered.
	std::shared_ptr<SpaceShip> buildSpaceShip(const std::string& spaceShipTypeName, const std::string& parameter);

	/// Makes a SpaceShip known to the factory.
	/// \param spaceShipTypeName	SpaceShip type identifier to register.
	/// \param createSpaceShipFunc	Function for creating the given SpaceShip.
	/// \throw std::exception if the spaceShipTypeName already exists.
	void registerSpaceShipType(const std::string& spaceShipTypeName, const CreateSpaceShipFunc& createSpaceShipFunc);

private:
	/// \brief private constructor
	SpaceShipFactory() {}
	/// \brief private copyconstructor
	SpaceShipFactory(SpaceShipFactory const&) {}
	/// \brief private = operator
	void operator=(SpaceShipFactory const&) {}


	/// Dictionary of all known SpaceShips
	std::unordered_map<std::string, CreateSpaceShipFunc> m_createSpaceShipFuncRegister;
};

