#ifndef SIREUM_H_base_test_data_port_periodic_domains_producer_thread_i_producer_producer
#define SIREUM_H_base_test_data_port_periodic_domains_producer_thread_i_producer_producer

#ifdef __cplusplus
extern "C" {
#endif

#include <types.h>

Unit base_test_data_port_periodic_domains_producer_thread_i_producer_producer_initialise(STACK_FRAME base_test_data_port_periodic_domains_producer_thread_i_Initialization_Api api);

Unit base_test_data_port_periodic_domains_producer_thread_i_producer_producer_timeTriggered(STACK_FRAME base_test_data_port_periodic_domains_producer_thread_i_Operational_Api api);

Unit base_test_data_port_periodic_domains_producer_thread_i_producer_producer_finalise(STACK_FRAME base_test_data_port_periodic_domains_producer_thread_i_Operational_Api api);

#ifdef __cplusplus
}
#endif

#endif