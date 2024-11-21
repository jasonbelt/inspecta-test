#include "consumer_p_s1_consumer.h"

void consumer_p_s1_consumer_initialize(void);
void handle_read_port(void);

volatile sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_t *read_port_queue_1;
sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_Recv_t read_port_recv_queue;

#define PORT_FROM_MON 58

bool read_port_is_empty(void) {
  return sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_is_empty(&read_port_recv_queue);
}

bool get_read_port_poll(sb_event_counter_t *numDropped, base_event_data_port_port_queues_ArrayOfStruct *data) {
  return sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_dequeue((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_Recv_t *) &read_port_recv_queue, numDropped, data);
}

bool get_read_port(base_event_data_port_port_queues_ArrayOfStruct *data) {
  sb_event_counter_t numDropped;
  return get_read_port_poll (&numDropped, data);
}

void init(void) {
  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_Recv_init(&read_port_recv_queue, (sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_t *) read_port_queue_1);

  consumer_p_s1_consumer_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      if (!read_port_is_empty()) {
        handle_read_port();
      }
      break;
  }
}
