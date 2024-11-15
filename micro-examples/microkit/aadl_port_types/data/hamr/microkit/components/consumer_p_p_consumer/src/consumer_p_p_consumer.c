#include "consumer_p_p_consumer.h"

void consumer_p_p_consumer_initialize(void);
void consumer_p_p_consumer_timeTriggered(void);

volatile sb_queue_int8_t_1_t *read_port_queue_1;
sb_queue_int8_t_1_Recv_t read_port_recv_queue;

#define PORT_FROM_MON 58

int8_t last_read_port_payload;

bool get_read_port(int8_t *data) {
  sb_event_counter_t numDropped;
  int8_t fresh_data;
  bool isFresh = false;
  if (sb_queue_int8_t_1_dequeue((sb_queue_int8_t_1_Recv_t *) &read_port_recv_queue, &numDropped, &fresh_data)) {
    last_read_port_payload = fresh_data;
    isFresh = true;
  }
  *data = last_read_port_payload;
  return isFresh;
}

void init(void) {
  sb_queue_int8_t_1_Recv_init(&read_port_recv_queue, (sb_queue_int8_t_1_t *) read_port_queue_1);

  consumer_p_p_consumer_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      consumer_p_p_consumer_timeTriggered();
      break;
  }
}
