#ifndef SIREUM_H_base_consumer_t_i_consumer_consumer_consumer
#define SIREUM_H_base_consumer_t_i_consumer_consumer_consumer

#ifdef __cplusplus
extern "C" {
#endif

#include <types.h>

void base_consumer_t_i_consumer_consumer_consumer_init(STACK_FRAME_ONLY);

base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge base_consumer_t_i_consumer_consumer_consumer_consumerBridge(STACK_FRAME_ONLY);
art_Bridge_EntryPoints base_consumer_t_i_consumer_consumer_consumer_entryPoints(STACK_FRAME_ONLY);
Option_8E9F45 base_consumer_t_i_consumer_consumer_consumer_noData(STACK_FRAME_ONLY);
art_Art_PortId base_consumer_t_i_consumer_consumer_consumer_read_port_id(STACK_FRAME_ONLY);
Option_8E9F45 base_consumer_t_i_consumer_consumer_consumer_read_port_port(STACK_FRAME_ONLY);
void base_consumer_t_i_consumer_consumer_consumer_read_port_port_a(STACK_FRAME Option_8E9F45 p_read_port_port);

Z base_consumer_t_i_consumer_consumer_consumer_main(STACK_FRAME IS_948B60 args);

Unit base_consumer_t_i_consumer_consumer_consumer_initialiseArchitecture(STACK_FRAME_ONLY);

Unit base_consumer_t_i_consumer_consumer_consumer_initialiseEntryPoint(STACK_FRAME_ONLY);

Unit base_consumer_t_i_consumer_consumer_consumer_computeEntryPoint(STACK_FRAME_ONLY);

Unit base_consumer_t_i_consumer_consumer_consumer_finaliseEntryPoint(STACK_FRAME_ONLY);

Unit base_consumer_t_i_consumer_consumer_consumer_touch_printDataContent(STACK_FRAME art_DataContent a);

Unit base_consumer_t_i_consumer_consumer_consumer_touch(STACK_FRAME_ONLY);

Unit base_consumer_t_i_consumer_consumer_consumer_logInfo(STACK_FRAME String title, String msg);

Unit base_consumer_t_i_consumer_consumer_consumer_logDebug(STACK_FRAME String title, String msg);

Unit base_consumer_t_i_consumer_consumer_consumer_logError(STACK_FRAME String title, String msg);

void base_consumer_t_i_consumer_consumer_consumer_getValue(STACK_FRAME Option_8E9F45 result, art_Art_PortId portId);

Unit base_consumer_t_i_consumer_consumer_consumer_sendOutput(STACK_FRAME IS_D10119 eventPortIds, IS_D10119 dataPortIds);

Unit base_consumer_t_i_consumer_consumer_consumer_receiveInput(STACK_FRAME IS_D10119 eventPortIds, IS_D10119 dataPortIds);

void base_consumer_t_i_consumer_consumer_consumer_init_consumerBridge(STACK_FRAME_ONLY);

void base_consumer_t_i_consumer_consumer_consumer_init_entryPoints(STACK_FRAME_ONLY);

void base_consumer_t_i_consumer_consumer_consumer_init_noData(STACK_FRAME_ONLY);

void base_consumer_t_i_consumer_consumer_consumer_init_read_port_id(STACK_FRAME_ONLY);

void base_consumer_t_i_consumer_consumer_consumer_init_read_port_port(STACK_FRAME_ONLY);

#ifdef __cplusplus
}
#endif

#endif