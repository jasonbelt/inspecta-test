#include "producer_p_p_producer.h"

void producer_p_p_producer_initialize(void);
void producer_p_p_producer_timeTriggered(void);

volatile sb_queue_base_data_1_prod_2_cons_struct_i_1_t *write_port_queue_1;

#define PORT_FROM_MON 60

bool put_write_port(const base_data_1_prod_2_cons_struct_i *data) {
  sb_queue_base_data_1_prod_2_cons_struct_i_1_enqueue((sb_queue_base_data_1_prod_2_cons_struct_i_1_t *) write_port_queue_1, (base_data_1_prod_2_cons_struct_i *) data);

  return true;
}

void init(void) {
  sb_queue_base_data_1_prod_2_cons_struct_i_1_init((sb_queue_base_data_1_prod_2_cons_struct_i_1_t *) write_port_queue_1);

  producer_p_p_producer_initialize();
}

void notified(microkit_channel channel) {
  switch (channel) {
    case PORT_FROM_MON:
      producer_p_p_producer_timeTriggered();
      break;
  }
}
