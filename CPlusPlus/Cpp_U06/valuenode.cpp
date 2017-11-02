#include "valuenode.hpp"


Node* Value::clone() const
{
	// Create a new instance with the same type and data. As you can see we
	// need to know the type ´Value´ that is why we cannot implement it
	// elsewhere.
	return new Value(m_val);
}

// TODO: evaluate implementation using string to int conversation functions
std::string Value::toString() const
{
	return m_val;
}

///      When trying to evaluate a string value first use
///		´_varMap->find(valueString)´. 
///		´find´ returns an iterator which is equal to _varMap->end() when
///		the key cannot be found.
///		If there is an entry with that name use its value, otherwise do a
///		node specific evaluation
int Value::evaluate(const VariableMap* _varMap) const
{
	VariableMap::const_iterator got = _varMap->find(m_val);

	if (got == _varMap->end()) 
	{
		return std::stoi(m_val);
	}
	else
	{
		return got->second;
	}
	
}