# Isolette

## Diagrams
### AADL/SysMLv2 Arch
![AADL Arch](aadl/diagrams/arch.svg)


## Installation


1. Install [Docker Desktop](https://www.docker.com/products/docker-desktop/)

1. Clone this repo and cd into it

   ```
   git clone https://github.com/loonwerks/INSPECTA-models.git
   cd INSPECTA-models
   ```

1. Clone the [SysMLv2 AADL Libraries](https://github.com/santoslab/sysml-aadl-libraries.git) into the Isolette's [sysml](sysml) directory

    ```
    git clone https://github.com/santoslab/sysml-aadl-libraries.git isolette/sysml/sysml-aadl-libraries
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

### JVM

1. *OPTIONAL* Rerun codegen targeting the JVM
      
   * From the [AADL model](aadl/aadl)

      Launch the Slash script [isolette/aadl/bin/run-hamr.cmd](aadl/bin/run-hamr.cmd) from the command line.  

      ```
      isolette/aadl/bin/run-hamr.cmd JVM
      ```
  
    * From the [SysMLv2 model](sysml)

      Launch the Slash script [isolette/sysml/bin/run-hamr.cmd](sysml/bin/run-hamr.cmd) from the command line.  

      ```
      isolette/sysml/bin/run-hamr.cmd JVM
      ```

1. Build and run the application

    ```
    sireum proyek run isolette/hamr/slang isolette.Demo
    ```

1. Verify code level contracts

    ```
    isolette/hamr/slang/bin/run-logika.cmd
    ```

1. Check model level intergration constraints

    ```
    sireum hamr sysml logika --sourcepath isolette/sysml
    ```

### Microkit

1. *OPTIONAL* Rerun codegen targeting Microkit
   
   * From the [AADL model](aadl/aadl)

      Launch the Slash script [isolette/aadl/bin/run-hamr.cmd](aadl/bin/run-hamr.cmd) from the command line.  

      ```
      isolette/aadl/bin/run-hamr.cmd Microkit
      ```

      Run the following to run an appraisal on the results (appraising will fail if any changes are made to the AADL files or the microkit.system file)

      ```
      docker run -it --rm -v $(pwd):/home/microkit/provers/INSPECTA-models jasonbelt/microkit_domain_scheduling \
        bash -ci "\$HOME/provers/INSPECTA-models/isolette/attestation/run-attestation.cmd aadl"
      ```
  
    * From the [SysMLv2 model](sysml)

      Launch the Slash script [isolette/sysml/bin/run-hamr.cmd](sysml/bin/run-hamr.cmd) from the command line.  

      ```
      isolette/sysml/bin/run-hamr.cmd Microkit
      ```

      Run the following to run an appraisal on the results (appraising will fail if any changes are made to the SysML files or the microkit.system file)

      ```
      docker run -it --rm -v $(pwd):/home/microkit/provers/INSPECTA-models jasonbelt/microkit_domain_scheduling \
        bash -ci "\$HOME/provers/INSPECTA-models/isolette/attestation/run-attestation.cmd sysml"
      ```

1. Build and simulate the seL4 Microkit image

    Run the following from this repository's root directory.  The docker image ``jasonbelt/microkit_domain_scheduling`` contains customized versions of Microkit and seL4 that support domain scheduling. They were built off the following pull requests

   - [microkit #175](https://github.com/seL4/microkit/pull/175)
   - [seL4 #1308](https://github.com/seL4/seL4/pull/1308)

    ```
    docker run -it --rm -v $(pwd):/home/microkit/provers/INSPECTA-models jasonbelt/microkit_domain_scheduling \
        bash -ci "cd \$HOME/provers/INSPECTA-models/isolette/hamr/microkit && make qemu"
    ```

    Type ``CTRL-a x`` to exit the QEMU simulation

    You should see output similar to the following

    ```
    Booting all finished, dropped to user space
    MON|INFO: Microkit Bootstrap
    MON|INFO: bootinfo untyped list matches expected list
    MON|INFO: Number of bootstrap invocations: 0x0000000a
    MON|INFO: Number of system invocations:    0x000002ab
    MON|INFO: completed bootstrap invocations
    MON|INFO: completed system invocations
    operator_interfa: Regulator Status: Init
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 0.000000
    operator_interfa: Alamr: off
    ####### FRAME 0 #######
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 97.000000
    operator_interfa: Alamr: off
    ####### FRAME 1 #######
    heat_source_cpi_: Received command: On
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 96.000000
    operator_interfa: Alamr: on
    ####### FRAME 2 #######
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 97.000000
    operator_interfa: Alamr: on
    ####### FRAME 3 #######
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 98.000000
    operator_interfa: Alamr: off
    ####### FRAME 4 #######
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 99.000000
    operator_interfa: Alamr: off
    ####### FRAME 5 #######
    heat_source_cpi_: Received command: Off
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 100.000000
    operator_interfa: Alamr: off
    ####### FRAME 6 #######
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 101.000000
    operator_interfa: Alamr: off
    ####### FRAME 7 #######
    operator_interfa: Regulator Status: On
    operator_interfa: Monitor Status: On
    operator_interfa: Display Temperature 102.000000
    operator_interfa: Alamr: on
    ```