#include <all.h>

// art.Bridge.EntryPoints

B art_Bridge_EntryPoints__is(STACK_FRAME void *this) {
  switch(((Type) this)->type) {
    case Tbase_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints: return T;
    default: return F;
  }
}

art_Bridge_EntryPoints art_Bridge_EntryPoints__as(STACK_FRAME void *this) {
  switch(((Type) this)->type) {
    case Tbase_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints: break;
    default:
      fprintf(stderr, "Invalid cast from %s to art.Bridge.EntryPoints.", TYPE_string_(this));
      sfAbortImpl(CALLER "");
  }
  return (art_Bridge_EntryPoints) this;
}

void art_Bridge_EntryPoints_string_(STACK_FRAME String result, art_Bridge_EntryPoints this);

Unit art_Bridge_EntryPoints_initialise_(STACK_FRAME art_Bridge_EntryPoints this) {
  switch (this->type) {
    case Tbase_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints: base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints_initialise_(CALLER (base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints) this); return;
    default: fprintf(stderr, "Infeasible TYPE: %s.\n", TYPE_string_(this)); exit(1);
  }
}

Unit art_Bridge_EntryPoints_compute_(STACK_FRAME art_Bridge_EntryPoints this) {
  switch (this->type) {
    case Tbase_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints: base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints_compute_(CALLER (base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints) this); return;
    default: fprintf(stderr, "Infeasible TYPE: %s.\n", TYPE_string_(this)); exit(1);
  }
}

Unit art_Bridge_EntryPoints_finalise_(STACK_FRAME art_Bridge_EntryPoints this) {
  switch (this->type) {
    case Tbase_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints: base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints_finalise_(CALLER (base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints) this); return;
    default: fprintf(stderr, "Infeasible TYPE: %s.\n", TYPE_string_(this)); exit(1);
  }
}