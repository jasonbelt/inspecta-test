#ifndef SIREUM_H_base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge
#define SIREUM_H_base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge

#ifdef __cplusplus
extern "C" {
#endif

#include <types.h>

void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_init(STACK_FRAME_ONLY);

Option_CFF091 base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_c_initialization_api(STACK_FRAME_ONLY);
void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_c_initialization_api_a(STACK_FRAME Option_CFF091 p_c_initialization_api);
Option_CF2493 base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_c_operational_api(STACK_FRAME_ONLY);
void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_c_operational_api_a(STACK_FRAME Option_CF2493 p_c_operational_api);

// base.test_event_port_periodic_domains.producer_t_i_producer_producer_Bridge

#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_id_(this) ((this)->id)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_name_(this) ((String) &(this)->name)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_dispatchProtocol_(this) ((art_DispatchPropertyProtocol) &(this)->dispatchProtocol)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_dispatchTriggers_(this) ((Option_0247A1) &(this)->dispatchTriggers)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_emit_(this) ((art_Port_9CBF18) &(this)->emit)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_ports_(this) ((art_Bridge_Ports) &(this)->ports)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_initialization_api_(this) ((base_test_event_port_periodic_domains_producer_t_i_Initialization_Api) &(this)->initialization_api)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_operational_api_(this) ((base_test_event_port_periodic_domains_producer_t_i_Operational_Api) &(this)->operational_api)
#define base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_entryPoints_(this) ((art_Bridge_EntryPoints) &(this)->entryPoints)

B base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__eq(base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this, base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge other);
inline B base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__ne(base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this, base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge other) {
  return !base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__eq(this, other);
};
B base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__equiv(base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this, base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge other);
inline B base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__inequiv(base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this, base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge other) {
  return !base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__equiv(this, other);
};
void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_string_(STACK_FRAME String result, base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this);
void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_cprint(base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this, B isOut);

inline B base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__is(STACK_FRAME void* this) {
  return ((base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge) this)->type == Tbase_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge;
}

inline base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__as(STACK_FRAME void *this) {
  if (base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge__is(CALLER this)) return (base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge) this;
  sfAbortImpl(CALLER "Invalid cast to base.test_event_port_periodic_domains.producer_t_i_producer_producer_Bridge.");
  abort();
}

void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_apply(STACK_FRAME base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge this, art_Art_BridgeId id, String name, art_DispatchPropertyProtocol dispatchProtocol, Option_0247A1 dispatchTriggers, art_Port_9CBF18 emit);

void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_init_c_initialization_api(STACK_FRAME_ONLY);

void base_test_event_port_periodic_domains_producer_t_i_producer_producer_Bridge_init_c_operational_api(STACK_FRAME_ONLY);

#ifdef __cplusplus
}
#endif

#endif