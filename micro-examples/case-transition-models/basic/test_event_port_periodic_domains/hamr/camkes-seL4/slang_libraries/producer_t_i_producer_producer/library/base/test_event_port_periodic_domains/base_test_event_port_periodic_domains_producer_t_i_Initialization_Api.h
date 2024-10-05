#ifndef SIREUM_H_base_test_event_port_periodic_domains_producer_t_i_Initialization_Api
#define SIREUM_H_base_test_event_port_periodic_domains_producer_t_i_Initialization_Api

#ifdef __cplusplus
extern "C" {
#endif

#include <types.h>

// base.test_event_port_periodic_domains.producer_t_i_Initialization_Api

#define base_test_event_port_periodic_domains_producer_t_i_Initialization_Api_id_(this) ((this)->id)
#define base_test_event_port_periodic_domains_producer_t_i_Initialization_Api_emit_Id_(this) ((this)->emit_Id)

B base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__eq(base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this, base_test_event_port_periodic_domains_producer_t_i_Initialization_Api other);
inline B base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__ne(base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this, base_test_event_port_periodic_domains_producer_t_i_Initialization_Api other) {
  return !base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__eq(this, other);
};
B base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__equiv(base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this, base_test_event_port_periodic_domains_producer_t_i_Initialization_Api other);
inline B base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__inequiv(base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this, base_test_event_port_periodic_domains_producer_t_i_Initialization_Api other) {
  return !base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__equiv(this, other);
};
void base_test_event_port_periodic_domains_producer_t_i_Initialization_Api_string_(STACK_FRAME String result, base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this);
void base_test_event_port_periodic_domains_producer_t_i_Initialization_Api_cprint(base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this, B isOut);

inline B base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__is(STACK_FRAME void* this) {
  return ((base_test_event_port_periodic_domains_producer_t_i_Initialization_Api) this)->type == Tbase_test_event_port_periodic_domains_producer_t_i_Initialization_Api;
}

inline base_test_event_port_periodic_domains_producer_t_i_Initialization_Api base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__as(STACK_FRAME void *this) {
  if (base_test_event_port_periodic_domains_producer_t_i_Initialization_Api__is(CALLER this)) return (base_test_event_port_periodic_domains_producer_t_i_Initialization_Api) this;
  sfAbortImpl(CALLER "Invalid cast to base.test_event_port_periodic_domains.producer_t_i_Initialization_Api.");
  abort();
}

void base_test_event_port_periodic_domains_producer_t_i_Initialization_Api_apply(STACK_FRAME base_test_event_port_periodic_domains_producer_t_i_Initialization_Api this, art_Art_BridgeId id, art_Art_PortId emit_Id);

#ifdef __cplusplus
}
#endif

#endif