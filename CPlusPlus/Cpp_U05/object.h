#pragma once
#include <iostream>
/// This object class serves basically as dummy for an "any object".
/// Later on we will learn different possibilities how we actually could store "anything" in here.
class Object
{
public:
	Object(const char* name);
	Object(const Object&);
  ~Object();

  /// Returns ID which was passed in the constructor.
  const char* GetName() const { return m_name; };

  void SetName(char* a) 
  { 
	  delete m_name;
	  m_name = a; 
  }
  /// Returns whether object is const or not
  const char* GetType() const { return "Const object"; };
  const char* GetType() { return "not const"; };
  /// Implementiert den angegebenen und die verwandten Operatoren
  inline bool operator==(const Object& o)
  {
	  return strcmp(this->m_name, o.m_name) == 0;
  }

  inline bool operator!=(const Object& o) {
	  return !(*this == o);
  }

  Object operator+ (Object& o) {
	  int len = strlen(this->m_name) + strlen(o.m_name);
	  char* my = new char[len]; 
	  snprintf(my, len, "%s%s", this->m_name, o.m_name);
	  Object* a = new Object(my);
	  return *a;
  }

  Object& operator+=(Object& o) {
	  this->m_name = strncat(this->m_name, o.m_name, strlen(o.m_name));
	  return *this;
  }

  // implement < and > for dynamicobjectlist.sort()
  inline bool operator< (const Object& rhs) {
	  return strcmp(this->m_name, rhs.m_name) < 0;
  }

  inline bool operator<= (const Object& rhs) {
	  return strcmp(this->m_name, rhs.m_name) <= 0;
  }

  inline bool operator> (const Object& rhs) {
	  return strcmp(this->m_name, rhs.m_name) > 0;
  }

  inline bool operator>= (const Object& rhs) {
	  return strcmp(this->m_name, rhs.m_name) >= 0;
  }

private:

  char* m_name;

  // Here could be YOUR data!
};
/// Implementiert den angegebenen und die verwandten Operatoren
std::ostream& operator<< (std::ostream& stream, const Object&);

std::istream& operator >> (std::istream& stream, const Object& o);
