#include "consumer_p_s_consumer.h"

void consumer_p_s_consumer_initialize(void);
void handle_read_port1(void);
void handle_read_port2(void);

volatile sb_queue_base_event_data_2_prod_2_cons_struct_i_1_t *read_port1_queue_1;
sb_queue_base_event_data_2_prod_2_cons_struct_i_1_Recv_t read_port1_recv_queue;
volatile sb_queue_base_event_data_2_prod_2_cons_struct_i_1_t *read_port2_queue_1;
sb_queue_base_event_data_2_prod_2_cons_struct_i_1_Recv_t read_port2_recv_queue;

#define PORT_FROM_MON 54

bool read_port1_is_empty(void) {
  return sb_queue_base_event_data_2_prod_2_cons_struct_i_1_is_empty(&read_port1_recv_queue);
}

bool get_read_port1_poll(sb_event_counter_t *numDropped, base_event_data_2_prod_2_cons_struct_i *data) {
  return sb_queue_base_event_data_2_prod_2_cons_struct_i_1_dequeue((sb_queue_base_event_data_2_prod_2_cons_struct_i_1_Recv_t *) &read_port1_recv_queue, numDropped, data);
}

bool get_read_port1(base_event_data_2_prod_2_cons_struct_i *data) {
  sb_event_counter_t numDropped;
  return get_read_port1_poll (&numDropped, data);
}

bool read_port2_is_empty(void) {
  return sb_queue_base_event_data_2_prod_2_cons_struct_i_1_is_empty(&read_port2_recv_queue);
}

bool get_read_port2_poll(sb_event_counter_t *numDropped, base_event_data_2_prod_2_cons_struct_i *data) {
  return sb_queue_base_event_data_2_prod_2_cons_struct_i_1_dequeue((sb_queue_base_event_data_2_prod_2_cons_struct_i_1_Recv_t *) &read_port2_recv_queue, numDropped, data);
}

bool get_read_port2(base_event_data_2_prod_2_cons_struct_i *data) {
  sb_event_counter_t numDropped;
  return get_read_port2_poll (&numDropped, data);
}

void init(void) {
  sb_queue_base_event_data_2_prod_2_cons_struct_i_1_Recv_init(&read_port1_recv_queue, (sb_queue_base_event_data_2_prod_2_cons_struct_i_1_t *) read_port1_queue_1);

  sb_queue_base_event_data_2_prod_2_cons_struct_i_1_Recv_init(&read_port2_recv_queue, (sb_queue_base_event_data_2_prod_2_cons_struct_i_1_t *) read_port2_queue_1);

  consumer_p_s_consumer_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      if (!read_port1_is_empty()) {
        handle_read_port1();
      }

      if (!read_port2_is_empty()) {
        handle_read_port2();
      }
      break;
  }
}
