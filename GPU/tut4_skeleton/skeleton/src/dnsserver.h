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

// includes, system
#include <string>
#include <map>
#include <mutex>
#include <shared_mutex>

class DNSServer {

public:

  typedef int DNSEntry;
  typedef std::pair< std::string, DNSEntry> DataEntry;

public:

  static DNSServer* get();

public:

  DNSEntry getDNS( const std::string& domain) const;

  void newEntry( const std::string& domain, const DNSEntry& dns);

private:

  std::map< std::string, int > data;

  // use shared_timed_mutex instead of shared_mutex since the latter one is
  // not yet implemented
  // can lock to read and lock to write
  mutable std::shared_timed_mutex  data_mutex;

private:

  static DNSServer* instance;

  // constructor, default
  DNSServer();

  // constructor, copy
  DNSServer( const DNSServer&);

  // operator, assignment
  DNSServer& operator=( const DNSServer&);

};
