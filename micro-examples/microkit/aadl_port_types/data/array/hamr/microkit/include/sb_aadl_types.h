#ifndef SB_AADL_TYPES_H
#define SB_AADL_TYPES_H

#include <stdint.h>

typedef struct base_data_1_prod_2_cons_struct_i base_data_1_prod_2_cons_struct_i;

struct base_data_1_prod_2_cons_struct_i {
  int32_t currentEvent;
  int32_t totalEventsSent;
};

#define base_data_1_prod_2_cons_ArrayOfStruct_SIZE 80
#define base_data_1_prod_2_cons_ArrayOfStruct_DIM 10

typedef base_data_1_prod_2_cons_struct_i base_data_1_prod_2_cons_ArrayOfStruct [base_data_1_prod_2_cons_ArrayOfStruct_DIM];

typedef
  struct base_data_1_prod_2_cons_ArrayOfStruct_container{
    base_data_1_prod_2_cons_ArrayOfStruct f;
  } base_data_1_prod_2_cons_ArrayOfStruct_container;

#endif
