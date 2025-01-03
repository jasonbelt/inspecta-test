// #Sireum
package isolette.Devices

import org.sireum._

// Do not edit this file as it will be overwritten if HAMR codegen is rerun

@msig trait Heat_Source_i_heat_source_cpi_heat_controller_Injection_Provider {
  def pre_receiveInput(): Unit
}

object Heat_Source_i_heat_source_cpi_heat_controller_Injection_Service {

  var providers: MSZ[Heat_Source_i_heat_source_cpi_heat_controller_Injection_Provider] = MSZ()

  def register(provider: Heat_Source_i_heat_source_cpi_heat_controller_Injection_Provider): Unit = {
    providers = providers :+ provider
  }

  def pre_receiveInput(): Unit = {
    for (provider <- providers) {
      provider.pre_receiveInput()
    }
  }
}