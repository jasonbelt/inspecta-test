#ifndef SIREUM_TYPE_H_base_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints
#define SIREUM_TYPE_H_base_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints

#ifdef __cplusplus
extern "C" {
#endif

#include <misc.h>

// base.test_event_port_periodic_domains.consumer_t_i_consumer_consumer_Bridge.EntryPoints
#include <type-art_Art_BridgeId.h>
#include <type-art_Art_PortId.h>
#include <type-org_sireum_Option_0247A1.h>
#include <type-base_test_event_port_periodic_domains_consumer_t_i_Initialization_Api.h>
#include <type-base_test_event_port_periodic_domains_consumer_t_i_Operational_Api.h>
#include <type-org_sireum_IS_D10119.h>
#include <type-org_sireum_IS_D10119.h>
#include <type-org_sireum_IS_D10119.h>
#include <type-org_sireum_IS_D10119.h>

typedef struct base_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints *base_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints;
struct base_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints {
  TYPE type;
  union Option_0247A1 dispatchTriggers;
  struct base_test_event_port_periodic_domains_consumer_t_i_Initialization_Api initialization_api;
  struct base_test_event_port_periodic_domains_consumer_t_i_Operational_Api operational_api;
  struct IS_D10119 dataInPortIds;
  struct IS_D10119 eventInPortIds;
  struct IS_D10119 dataOutPortIds;
  struct IS_D10119 eventOutPortIds;
  art_Art_BridgeId consumer_t_i_consumer_consumer_BridgeId;
  art_Art_PortId consume_Id;
};

#define DeclNewbase_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints(x) struct base_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints x = { .type = Tbase_test_event_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints }

#ifdef __cplusplus
}
#endif

#endif