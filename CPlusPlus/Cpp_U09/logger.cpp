#define _CRT_SECURE_NO_WARNINGS	// For Windows-Users (ctime gets warnings) - all other can delete or ignore this line
#include <ctime>

#include "logger.hpp"
#include "policy.hpp"

/// \brief Helper function to get a time-string with second resolution.
static std::string getTimeString()
{
	std::string timeStr;
	time_t rawTime;
	time( &rawTime );
	timeStr = ctime( &rawTime );
	return timeStr.substr( 0 , timeStr.size() - 1 );
}

Logger::Logger() {
}

/// \brief Send a message to each registered policy.
/// \details Adds specific information about location and time to the
///		message before output.
/// \param [in] file The file of the calling code. Use __FILE__ to get
///		this information.
/// \param [in] line The line of code where the log message is sent.
///		Use __LINE__ to get this information.
/// \param [in] message A problem specific message text.
// TODO: Implement this method
// Hint: to get a nice time string use the utility function in logger.cpp
void Logger::write(const std::string& file, long line, const std::string& message) {
	std::string completeM = "";
	completeM += getTimeString() + " " + file + " ";
	completeM += line + " " + message + "\n";
	m_history += completeM;

	for (int i = 0; i < m_policies.size(); ++i) {
		m_policies.at(i)->write(completeM);
	}
}

Logger& Logger::instance() {
	static Logger oneLogger;
	return oneLogger;
}

void Logger::registerPolicy(std::unique_ptr<Policy> _policy) {
	m_policies.push_back(std::move(_policy));
	m_policies.back()->write(m_history);
}