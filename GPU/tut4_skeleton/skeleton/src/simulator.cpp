/// @file
////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Copyright (C) 2017/18      Christian Lessig, Otto-von-Guericke Universitaet Magdeburg
///
////////////////////////////////////////////////////////////////////////////////////////////////////
///
///  module     : lecture 3
///
///  author     : lessig@isg.cs.ovgu.de
///
///  project    : GPU Programming
///
///  description: race conditions
///
////////////////////////////////////////////////////////////////////////////////////////////////////

// includes, file
#include "simulator.h"

// includes, system
#include <iostream>
#include <iomanip>
#include <vector>
#include <thread>
#include <cstdlib>
#include <chrono>
#include <ctime>

// includes, project
#include "dnsserver.h"

namespace Simulator {

////////////////////////////////////////////////////////////////////////////////////////////////////
// client simulator ask about DNS entry
////////////////////////////////////////////////////////////////////////////////////////////////////
void
client( int tid, int num_threads) {

  DNSServer* server = DNSServer::get();
  std::string domains[] = { "www.ovgu.de", "www.tu-berlin.de"};
  int ndomains = 2;

  for( int i = 0; i < 10; ++i) {

    std::chrono::milliseconds timespan( rand() % 1000);
    std::cout << tid << " sleeping for " << timespan.count() << std::endl;
    std::this_thread::sleep_for(timespan);

    DNSServer::DNSEntry entry = server->getDNS( domains[i % ndomains]);
    std::cout << tid << " got " << entry << std::endl;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// admin simulator adds new entrys
////////////////////////////////////////////////////////////////////////////////////////////////////
void
admin() {

  DNSServer* server = DNSServer::get();

  // put in some new entries
  std::vector< std::pair< std::string, int > > new_entries;
  new_entries.push_back(std::pair<std::string,DNSServer::DNSEntry>("www.hu-berlin.de", 141205188));
  new_entries.push_back(std::pair<std::string,DNSServer::DNSEntry>("www.fu-berlin.de", 1604517010));
  new_entries.push_back(std::pair<std::string,DNSServer::DNSEntry>("www.ethz", 12913219216));

  for( auto& entry : new_entries) {

    std::chrono::milliseconds timespan( rand() % 1000);
    std::this_thread::sleep_for(timespan);

    server->newEntry( entry.first, entry.second);
  }

}

////////////////////////////////////////////////////////////////////////////////////////////////////
// interface
////////////////////////////////////////////////////////////////////////////////////////////////////
void
run() {

  srand( time( NULL));

  // start DNS Server
  DNSServer* server = DNSServer::get();

  unsigned int num_client_threads = 4;
  const unsigned int num_admin_threads = 1;
  std::vector<std::thread> threads;

  // start clients
  for(int i = 0; i < num_client_threads; ++i)
  {
	  threads.push_back(std::thread(client, i, num_client_threads + num_admin_threads));
  }

  // start admin
  for (int i = 0; i < num_admin_threads; ++i) {
	  threads.push_back(std::thread(admin));
  }

  // collect the threads
  for (auto& th : threads) {
	  th.join();
  }

  // clean-up
  delete server;
}

}
