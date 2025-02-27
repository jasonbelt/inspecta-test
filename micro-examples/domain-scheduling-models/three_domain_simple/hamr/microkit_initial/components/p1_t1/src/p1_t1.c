#include "p1_t1.h"

// Do not edit this file as it will be overwritten if codegen is rerun

void p1_t1_initialize(void);
void p1_t1_notify(microkit_channel channel);
void p1_t1_timeTriggered(void);

volatile sb_queue_int32_t_1_t *write_port_queue_1;

#define PORT_FROM_MON 60

bool put_write_port(const int32_t *data) {
  sb_queue_int32_t_1_enqueue((sb_queue_int32_t_1_t *) write_port_queue_1, (int32_t *) data);

  return true;
}

void init(void) {
  sb_queue_int32_t_1_init((sb_queue_int32_t_1_t *) write_port_queue_1);

  p1_t1_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      p1_t1_timeTriggered();
      break;
    default:
      p1_t1_notify(channel);
  }
}
