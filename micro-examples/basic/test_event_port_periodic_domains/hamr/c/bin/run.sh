#!/usr/bin/env bash
#
# Do not edit this file as it will be overwritten if HAMR codegen is rerun
#
set -e
export SCRIPT_HOME=$( cd "$( dirname "$0" )" &> /dev/null && pwd )
cd $SCRIPT_HOME

# Uncomment the following to prevent terminal from closing when the app shuts down or crashes
#PREVENT_CLOSE="; bash -i"

# check if getopt supports long options
getopt -T > /dev/null || ret=$?
[[ $ret -eq 4 ]] && GNU_GETOPT=0 || GNU_GETOPT=1

OPTIONS=s:h
LONGOPTS=scheduler:,help

function usage {
  echo ""
  echo "Usage: <option>*"
  echo ""
  echo "Available Options:"
  if [[ $GNU_GETOPT -eq 0 ]]; then
    echo "-s, --scheduler        The scheduler to use (expects one of"
    echo "                         { default, roundRobin, static, legacy};"
    echo "                         default: default)"
    echo "-h, --help             Display this information"
  else
    echo "-s                     The scheduler to use (expects one of"
    echo "                         { default, roundRobin, static, legacy};"
    echo "                         default: default)"
    echo "-h                     Display this information"
  fi
}

if [[ $GNU_GETOPT -eq 0 ]]; then
  ! PARSED=$(getopt --options=$OPTIONS --longoptions=$LONGOPTS --name "$0" -- "$@")
else
  ! PARSED=$(getopt $OPTIONS "$@")
fi

if [[ ${PIPESTATUS[0]} -ne 0 ]]; then
  usage
  exit 1
fi

eval set -- "$PARSED"

SCHEDULER="default"
while true; do
  case "$1" in
    -h|--help) usage; exit 0 ;;
    -s|--scheduler)
      case "$2" in
        default|roundRobin|static|legacy)
          SCHEDULER="$2" ;;
        *)
          echo "Invalid scheduler: ${2}"
          exit 2 ;;
      esac
      shift 2 ;;
    --) shift; break ;;
  esac
done

# handle non-option arguments
if [[ $# -ne 0 ]]; then
  echo "$0: Unexpected non-option arguments"
  usage
  exit 3
fi

function launch() {
  if [ "$2" ]; then SCHEDULER_ARG=" -s ${2}"; fi
  if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then
    for APP in $1; do
      cygstart mintty /bin/bash -c "slang-build/${APP}${SCHEDULER_ARG}${PREVENT_CLOSE}" &
    done
  elif [[ "$(uname)" == "Darwin" ]]; then
    for APP in $1; do
      # workaround to launch the applications via separate Terminals. Create a shell script in the
      # /tmp directory that launches the application. Then delete the shell script when the
      # application exits
      echo "${SCRIPT_HOME}/slang-build/${APP}${SCHEDULER_ARG}${PREVENT_CLOSE} ; rm /tmp/${APP}.sh" > /tmp/${APP}.sh ; chmod +x /tmp/${APP}.sh ; open -a Terminal /tmp/${APP}.sh &
    done
  elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" ]]; then
    for APP in $1; do
      x-terminal-emulator -T ${APP} -e sh -i -c "slang-build/${APP}${SCHEDULER_ARG}${PREVENT_CLOSE}" &
    done
  else
    >&2 echo "Platform not supported: $(uname)."
    exit 1
  fi
}

EXT=""
if [ -n "$COMSPEC" -a -x "$COMSPEC" ]; then EXT=".exe"; fi

case "${SCHEDULER}" in
  legacy)
    if [ ! -f ./slang-build/LegacyDemo${EXT} ]; then
      if [ -f ./slang-build/Demo${EXT} ]; then
        echo "Error: Found program for Slang based schedulers.  Pass '--legacy' to the"
        echo "transpiler script in order to use the legacy scheduler"
      else
        echo "Expected program not found, have you compiled? ${SCRIPT_HOME}/slang-build/LegacyDemo${EXT}"
      fi
      exit 1
    fi

    launch "producer_t_i_producer_producer_App${EXT} consumer_t_i_consumer_consumer_App${EXT}";

    read -p "Press enter to initialise components ..."
    slang-build/LegacyDemo${EXT}
    read -p "Press enter again to start ..."
    slang-build/LegacyDemo${EXT}
    ;;
  *)
    if [ ! -f ./slang-build/Demo${EXT} ]; then
      if [ -f ./slang-build/LegacyDemo${EXT} ]; then
        echo "Error: Found program for the legacy scheduler. Either pass '-s legacy' to the"
        echo "run script if you want to use the legacy scheduler, or, do not pass"
        echo "'--legacy' to the transpiler script if you want to use a Slang based scheduler"
      else
        echo "Expected program not found, have you compiled? ${SCRIPT_HOME}/slang-build/Demo${EXT}"
      fi
      exit 1
    fi

    launch "Demo" ${SCHEDULER};
    ;;
esac
