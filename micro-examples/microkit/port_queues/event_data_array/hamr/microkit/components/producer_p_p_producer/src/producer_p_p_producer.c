#include "producer_p_p_producer.h"

void producer_p_p_producer_initialize(void);
void producer_p_p_producer_timeTriggered(void);

volatile sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_t *write_port_queue_1;
volatile sb_queue_base_event_data_port_port_queues_ArrayOfStruct_2_t *write_port_queue_2;
volatile sb_queue_base_event_data_port_port_queues_ArrayOfStruct_5_t *write_port_queue_5;

#define PORT_FROM_MON 60

bool put_write_port(const base_event_data_port_port_queues_ArrayOfStruct *data) {
  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_enqueue((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_t *) write_port_queue_1, (base_event_data_port_port_queues_ArrayOfStruct *) data);

  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_2_enqueue((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_2_t *) write_port_queue_2, (base_event_data_port_port_queues_ArrayOfStruct *) data);

  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_5_enqueue((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_5_t *) write_port_queue_5, (base_event_data_port_port_queues_ArrayOfStruct *) data);

  return true;
}

void init(void) {
  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_init((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_1_t *) write_port_queue_1);

  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_2_init((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_2_t *) write_port_queue_2);

  sb_queue_base_event_data_port_port_queues_ArrayOfStruct_5_init((sb_queue_base_event_data_port_port_queues_ArrayOfStruct_5_t *) write_port_queue_5);

  producer_p_p_producer_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      producer_p_p_producer_timeTriggered();
      break;
  }
}
