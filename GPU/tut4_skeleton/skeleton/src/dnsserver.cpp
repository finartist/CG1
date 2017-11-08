/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : tutorial 4
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: race conditions
///
////////////////////////////////////////////////////////////////////////////////////////////////////

// includes, file
#include "dnsserver.h"

// TODO

typedef int DNSEntry;
typedef std::pair< std::string, DNSEntry> DataEntry;

DNSServer* DNSServer::instance = nullptr;

DNSServer* DNSServer::get() {
	if (instance != nullptr) {
		return instance;
	}
	instance = new DNSServer();
	return instance;
}

DNSEntry DNSServer::getDNS(const std::string& domain) const {
	std::shared_lock<std::shared_timed_mutex> lockguard(data_mutex);
	DNSEntry entry = -1;
	try {
		 entry = data.at(domain);
	}
	catch(const std::exception& ex){

	}

	return entry;
}

void DNSServer::newEntry(const std::string& domain, const DNSEntry& dns) {
	
	std::lock_guard<std::shared_timed_mutex> lockguard(data_mutex);
	data.insert(std::pair<std::string, DNSEntry>(domain, dns));
}

DNSServer::DNSServer() :data(), data_mutex() {
	data.insert(std::pair<std::string, DNSEntry>("www.ovgu.de", 289847821074308));
	data.insert(std::pair<std::string, DNSEntry>("www.tu-berlin.de", 7897023764070));
}

DNSServer::DNSServer(const DNSServer& original) : data_mutex() {
	data = original.data;
}

DNSServer& DNSServer::operator= (const DNSServer& k) {
	if (&k != this) {
		//Do stuff
	}
	return *this;
}