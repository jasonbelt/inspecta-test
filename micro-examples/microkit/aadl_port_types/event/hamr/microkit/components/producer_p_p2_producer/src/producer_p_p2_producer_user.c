#include "producer_p_p2_producer.h"

void producer_p_p2_producer_initialize(void) {
  // event ports so nothing to init
  printf("%s: I'm periodic\n", microkit_name);
}

int counter = 0;
void producer_p_p2_producer_timeTriggered(void) {
  printf("%s: ", microkit_name);
  if (counter % 3 == 0) {
    put_write_port();
    printf("sent an event\n");
  } else {
    printf("no send\n");
  }
  counter = counter + 1;
}