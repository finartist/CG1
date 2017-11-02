#include "RandomChar.h"

//added RandomChar:: because else LinkerError LNK2001 (could not find m_Length, because did not know where to look for it)
int const RandomChar::m_Length = 71;
char const RandomChar::m_AllPossibleChars[m_Length] = { "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!§$%&?@" };

RandomChar::RandomChar()
{
}

RandomChar::~RandomChar()
{
}