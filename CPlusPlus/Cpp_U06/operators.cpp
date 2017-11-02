#include "operators.hpp"

#include <cassert>

// ************************************************************************* //
OpAdd::OpAdd(const Node& lhs, const Node& rhs)
{
	m_children[0] = lhs.clone();
	m_children[1] = rhs.clone();
}

OpAdd::OpAdd(Node* lhs, Node* rhs)
{
	m_children[0] = lhs;
	m_children[1] = rhs;
}

OpAdd::~OpAdd()
{
	for (int i = 0; i < 2; ++i) {
		delete m_children[i];
	}
	//Array liegt auf Stack? -> kein delete möglich (wenn drin wirft fehler in OpMult)
	//delete[] &m_children;
}

Node* OpAdd::clone() const
{
	// Cloning requires a deep copy. Otherwise multiple pointer would address
	// the same memory.
	return new OpAdd(m_children[0]->clone(), m_children[1]->clone());
}

std::string OpAdd::toString() const
{
	std::string a = " + ";
	return m_children[0]->toString() + a + m_children[1]->toString();
}

int OpAdd::evaluate(const VariableMap* _varMap) const
{
	return m_children[0]->evaluate(_varMap) + m_children[1]->evaluate(_varMap);
};


// TODO: the other methods


// ************************************************************************* //
OpMul::OpMul(const Node& lhs, const Node& rhs)
{
	m_children[0] = lhs.clone();
	m_children[1] = rhs.clone();
}

OpMul::OpMul(Node* lhs, Node* rhs)
{
	m_children[0] = lhs;
	m_children[1] = rhs;
}

OpMul::~OpMul()
{
	for (int i = 0; i < 2; ++i) {
		delete m_children[i];
	}
	//Array liegt auf Stack? -> kein delete möglich (wenn drin wirft fehler in OpMult)
	//delete[] m_children;
}

Node* OpMul::clone() const
{
	// Cloning requires a deep copy. Otherwise multiple pointer would address
	// the same memory.
	return new OpMul(m_children[0]->clone(), m_children[1]->clone());
}

std::string OpMul::toString() const
{
	std::string a = " * ";
	return m_children[0]->toString() + a + m_children[1]->toString();
}

int OpMul::evaluate(const VariableMap* _varMap) const
{
	return m_children[0]->evaluate(_varMap) * m_children[1]->evaluate(_varMap);
};

// TODO: the other methods


// ************************************************************************* //
OpNegate::OpNegate(const Node& lhs)
{
	m_child = lhs.clone();
}


OpNegate::OpNegate(Node* rhs)
{
	m_child = rhs;
}

OpNegate::~OpNegate()
{
	delete m_child;
}

Node* OpNegate::clone() const
{
	// Cloning requires a deep copy. Otherwise multiple pointer would address
	// the same memory.
	return new OpNegate(m_child->clone());
}

std::string OpNegate::toString() const
{
	std::string a = " -";
	return a + "(" + m_child->toString() + ")";
}

int OpNegate::evaluate(const VariableMap* _varMap) const
{
	return 0 - m_child->evaluate(_varMap);
};

// TODO: the other methods