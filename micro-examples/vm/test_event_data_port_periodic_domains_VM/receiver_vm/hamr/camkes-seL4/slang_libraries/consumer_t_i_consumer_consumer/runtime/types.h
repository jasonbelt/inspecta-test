#ifndef SIREUM_GEN_H
#define SIREUM_GEN_H

#ifdef __cplusplus
extern "C" {
#endif

#include <misc.h>
#include <type-art_Art.h>
#include <type-art_Art_BridgeId.h>
#include <type-art_Art_PortId.h>
#include <type-art_Bridge.h>
#include <type-art_Bridge_EntryPoints.h>
#include <type-art_Bridge_Ports.h>
#include <type-art_DataContent.h>
#include <type-art_DispatchPropertyProtocol.h>
#include <type-art_DispatchPropertyProtocol_Periodic.h>
#include <type-art_Empty.h>
#include <type-art_PortMode_Type.h>
#include <type-art_UPort.h>
#include <type-art_art_Port_45E54D.h>
#include <type-base_Base_Types.h>
#include <type-base_Base_Types_Bits_Payload.h>
#include <type-base_Base_Types_Boolean_Payload.h>
#include <type-base_TranspilerToucher.h>
#include <type-base_consumer_t_i_consumer_consumer_consumer.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_Api.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_Initialization_Api.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_Operational_Api.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_Bridge_EntryPoints.h>
#include <type-base_test_event_data_port_periodic_domains_consumer_t_i_consumer_consumer_seL4Nix.h>
#include <type-org_sireum_IS_820232.h>
#include <type-org_sireum_IS_948B60.h>
#include <type-org_sireum_IS_C4F575.h>
#include <type-org_sireum_IS_D10119.h>
#include <type-org_sireum_MBox2_37E193.h>
#include <type-org_sireum_MBox2_43CC67.h>
#include <type-org_sireum_MS_83D5EB.h>
#include <type-org_sireum_None.h>
#include <type-org_sireum_None_3026C5.h>
#include <type-org_sireum_None_539999.h>
#include <type-org_sireum_None_734370.h>
#include <type-org_sireum_None_964667.h>
#include <type-org_sireum_None_CFD2B0.h>
#include <type-org_sireum_None_F1EF25.h>
#include <type-org_sireum_Nothing.h>
#include <type-org_sireum_Option_0247A1.h>
#include <type-org_sireum_Option_0CB775.h>
#include <type-org_sireum_Option_30119F.h>
#include <type-org_sireum_Option_7BBFBE.h>
#include <type-org_sireum_Option_8E9F45.h>
#include <type-org_sireum_Option_F1A21C.h>
#include <type-org_sireum_Some.h>
#include <type-org_sireum_Some_2159FF.h>
#include <type-org_sireum_Some_6B73B7.h>
#include <type-org_sireum_Some_8D03B1.h>
#include <type-org_sireum_Some_D29615.h>
#include <type-org_sireum_U8.h>

#if defined(static_assert)
#define STATIC_ASSERT static_assert
#define GLOBAL_STATIC_ASSERT(a, b, c) static_assert(b, c)
#else
#define STATIC_ASSERT(pred, explanation); {char assert=1/(pred);(void)assert;}
#define GLOBAL_STATIC_ASSERT(unique, pred, explanation); namespace ASSERTION {char unique=1/(pred);}
#endif

size_t sizeOf(Type t);
void Type_assign(void *dest, void *src, size_t destSize);

#ifdef __cplusplus
}
#endif

#endif