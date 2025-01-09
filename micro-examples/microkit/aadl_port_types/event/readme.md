# AADL Event Ports

 Table of Contents
  * [Diagrams](#diagrams)
    * [AADL Arch](#aadl-arch)
  * [Metrics](#metrics)
    * [AADL Metrics](#aadl-metrics)

## Diagrams
### AADL Arch
![AADL Arch](aadl/diagrams/arch.svg)

## Metrics
### AADL Metrics
| | |
|--|--|
|Threads|4|
|Ports|6|
|Connections|4|


## Installation


1. Install [Docker Desktop](https://www.docker.com/products/docker-desktop/)

1. Clone this repo and cd into it

   ```
   git clone https://github.com/loonwerks/INSPECTA-models.git
   cd INSPECTA-models
   ```

1. *OPTIONAL*

    If you want to rerun codegen then you will need to install Sireum
    and OSATE.  You can do this inside or outside of the container that you'll pull in the next section (the latter is probably preferable as you could then use Sireum outside of the container).

    Copy/paste the following to install Sireum
    ```
    git clone https://github.com/sireum/kekinian.git
    ```

    This installs/builds Sireum from source rather than via a binary distribution (which is probably the prefered method for PROVERS).  

    Now set ``SIREUM_HOME`` to point to where you cloned kekinian and add ``$SIREUM_HOME/bin`` to your path.  E.g. for bash

    ```
    echo "export SIREUM_HOME=$(pwd)/kekinian" >> $HOME/.bashrc
    echo "export PATH=\$SIREUM_HOME/bin:\$PATH" >> $HOME/.bashrc
    source $HOME/.bashrc
    ```

    To update Sireum in the future do the following
    ```
    cd $SIREUM_HOME
    git pull --rec
    bin/build.cmd
    ```

    Run the following to install IVE and CodeIVE which provide IDE support for Slang and SysMLv2 respectively.
    ```
    sireum setup ive
    sireum setup vscode
    ```

    Run the following to install OSATE and the Sireum plugins which provides IDE and codegen support for AADL. This will install OSATE into your current directory (or wherever as indicated via the ``-o`` option).  For Windows/Linux 
    ```
    sireum hamr phantom -u -v -o $(pwd)/osate
    ```

    or for Mac copy/paste
    ```
    sireum hamr phantom -u -v -o $(pwd)/osate.app
    ```

    Now set ``OSATE_HOME`` to point to where you installed Osate

    ```
    echo "export OSATE_HOME=$(pwd)/osate" >> $HOME/.bashrc
    source $HOME/.bashrc
    ```

## Codegen

1. *OPTIONAL* Rerun codegen targetting Microkit
   
    Launch the Slash script [micro-examples/microkit/aadl_port_types/event/aadl/bin/run-hamr.cmd](aadl/bin/run-hamr.cmd) from the command line.  

   ```
   micro-examples/microkit/aadl_port_types/event/aadl/bin/run-hamr.cmd
   ```

   Run the following to do an appraisal on the results (appraising will fail if any changes are made to the AADL files or the microkit.system file)

   ```
   docker run -it --rm -v $(pwd):/home/microkit/provers/INSPECTA-models jasonbelt/microkit_domain_scheduling \
      bash -ci "\$HOME/provers/INSPECTA-models/micro-examples/microkit/aadl_port_types/event/attestation/run-attestation.cmd aadl"
   ``` 
   
1. Build and simulate the seL4 Microkit image

    Run the following from this repository's root directory.  The docker image ``jasonbelt/microkit_domain_scheduling`` contains customized versions of Microkit and seL4 that support domain scheduling. They were built off the following pull requests

   - [microkit #175](https://github.com/seL4/microkit/pull/175)
   - [seL4 #1308](https://github.com/seL4/seL4/pull/1308)

    ```
    docker run -it --rm -v $(pwd):/home/microkit/provers/INSPECTA-models jasonbelt/microkit_domain_scheduling \
      bash -ci "cd \$HOME/provers/INSPECTA-models/micro-examples/microkit/aadl_port_types/event/hamr/microkit \
                && make qemu"
    ```

    Type ``CTRL-a x`` to exit the QEMU simulation
    
    You should get output similar to
    ```
    Booting all finished, dropped to user space
    MON|INFO: Microkit Bootstrap
    MON|INFO: bootinfo untyped list matches expected list
    MON|INFO: Number of bootstrap invocations: 0x00000009
    MON|INFO: Number of system invocations:    0x000000fe
    MON|INFO: completed bootstrap invocations
    MON|INFO: completed system invocations
    producer_p_p2_pr: I'm periodic
    consumer_p_p_con: I'm periodic
    consumer_p_s_con: I'm sporadic
    producer_p_p1_pr: I'm periodic
    producer_p_p2_pr: sent an event
    consumer_p_p_con: nothing received on read port 1
    consumer_p_p_con: received a read port 2 event
    consumer_p_s_con: received a read port 2 event
    -------
    producer_p_p1_pr: sent an event
    producer_p_p2_pr: no send
    consumer_p_p_con: received a read port 1 event
    consumer_p_p_con: nothing received on read port 2
    consumer_p_s_con: received a read port 1 event
    -------
    producer_p_p1_pr: no send
    producer_p_p2_pr: no send
    consumer_p_p_con: nothing received on read port 1
    consumer_p_p_con: nothing received on read port 2
    -------
    producer_p_p1_pr: sent an event
    producer_p_p2_pr: sent an event
    consumer_p_p_con: received a read port 1 event
    consumer_p_p_con: received a read port 2 event
    consumer_p_s_con: received a read port 1 event
    consumer_p_s_con: received a read port 2 event
    -------
    producer_p_p1_pr: no send
    producer_p_p2_pr: no send
    consumer_p_p_con: nothing received on read port 1
    consumer_p_p_con: nothing received on read port 2
    -------
    producer_p_p1_pr: sent an event
    producer_p_p2_pr: no send
    consumer_p_p_con: received a read port 1 event
    consumer_p_p_con: nothing received on read port 2
    consumer_p_s_con: received a read port 1 event
    ```