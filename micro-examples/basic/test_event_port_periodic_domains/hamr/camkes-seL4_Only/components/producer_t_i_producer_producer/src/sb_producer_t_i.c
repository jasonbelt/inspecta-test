// Do not edit this file as it will be overwritten if HAMR codegen is rerun

#include <sb_producer_t_i.h>
#include <sb_event_counter.h>
#include <string.h>
#include <camkes.h>

void sb_entrypoint_period_producer_t_i(int64_t *in_arg) {
  compute((int64_t *) in_arg);
}

/************************************************************************
 * sb_emit_enqueue
 * Invoked from user code in the local thread.
 *
 * This is the function invoked by the local thread to make a
 * call to send to a remote event port.
 *
 ************************************************************************/
bool sb_emit_enqueue(void) {
  // sb_emit_counter is a dataport (shared memory) that is written by the sender
  // and read by the receiver(s). This counter is monotonicly increasing,
  // but can wrap.
  (*sb_emit_counter)++;

  // Release memory fence - ensure subsequent write occurs after any preceeding read or write
  sb_emit_counter_release();

  sb_emit_emit();

  return true;
}


/************************************************************************
 *  sb_entrypoint_producer_t_i_producer_producer_initializer:
 *
 * This is the function invoked by an active thread dispatcher to
 * call to a user-defined entrypoint function.  It sets up the dispatch
 * context for the user-defined entrypoint, then calls it.
 *
 ************************************************************************/
void sb_entrypoint_producer_t_i_producer_producer_initializer(const int64_t * in_arg) {
  init((int64_t *) in_arg);
}

void pre_init(void) {
  // initialise shared counter for event port emit
  *sb_emit_counter = 0;
}

/************************************************************************
 * int run(void)
 * Main active thread function.
 ************************************************************************/
int run(void) {

  {
    int64_t sb_dummy;
    sb_entrypoint_producer_t_i_producer_producer_initializer(&sb_dummy);
  }
  sb_self_pacer_tick_emit();
  for(;;) {
    sb_self_pacer_tock_wait();

    {
      int64_t sb_dummy = 0;
      sb_entrypoint_period_producer_t_i(&sb_dummy);
    }
    sb_self_pacer_tick_emit();
  }
  return 0;
}
