#include "p2_t2.h"

// Do not edit this file as it will be overwritten if codegen is rerun

void p2_t2_initialize(void);
void p2_t2_notify(microkit_channel channel);
void p2_t2_timeTriggered(void);

volatile sb_queue_int32_t_1_t *read_port_queue_1;
sb_queue_int32_t_1_Recv_t read_port_recv_queue;
volatile sb_queue_int32_t_1_t *write_port_queue_1;

#define PORT_FROM_MON 58

int32_t last_read_port_payload;

bool get_read_port(int32_t *data) {
  sb_event_counter_t numDropped;
  int32_t fresh_data;
  bool isFresh = sb_queue_int32_t_1_dequeue((sb_queue_int32_t_1_Recv_t *) &read_port_recv_queue, &numDropped, &fresh_data);
  if (isFresh) {
    last_read_port_payload = fresh_data;
  }
  *data = last_read_port_payload;
  return isFresh;
}

bool put_write_port(const int32_t *data) {
  sb_queue_int32_t_1_enqueue((sb_queue_int32_t_1_t *) write_port_queue_1, (int32_t *) data);

  return true;
}

void init(void) {
  sb_queue_int32_t_1_Recv_init(&read_port_recv_queue, (sb_queue_int32_t_1_t *) read_port_queue_1);

  sb_queue_int32_t_1_init((sb_queue_int32_t_1_t *) write_port_queue_1);

  p2_t2_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      p2_t2_timeTriggered();
      break;
    default:
      p2_t2_notify(channel);
  }
}