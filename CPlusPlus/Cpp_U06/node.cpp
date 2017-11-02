#include "node.hpp"

// TODO: streaming operator implementation
std::ostream& operator << (std::ostream& stream, const Node& node)
{
	stream << node.toString();
	return stream;
}