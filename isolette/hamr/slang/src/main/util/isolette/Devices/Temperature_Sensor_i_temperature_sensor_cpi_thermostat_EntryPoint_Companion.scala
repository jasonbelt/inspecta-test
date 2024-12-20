// #Sireum

package isolette.Devices

import org.sireum._
import art._
import isolette._

// Do not edit this file as it will be overwritten if HAMR codegen is rerun

object Temperature_Sensor_i_temperature_sensor_cpi_thermostat_EntryPoint_Companion {

  var last_api_current_tempWstatus: Option[Isolette_Data_Model.TempWstatus_i] = None()

  /** get the value of outgoing data port current_tempWstatus.  If a 'fresh' value wasn't sent
    * during the last dispatch then return last_api_current_tempWstatus.get.
    * Note: this requires outgoing data ports to be initialized during the
    * initialization phase or prior to system testing.
    */
  def get_api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i = {
    Art.observeOutPortVariable(Arch.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat.operational_api.current_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_i_Payload(value)) =>
        last_api_current_tempWstatus = Some(value)
        return value
      case _ if last_api_current_tempWstatus.isEmpty =>
        assert(F, "No value found on outgoing data port current_tempWstatus.\n                  Note: values placed during the initialization phase will persist across dispatches")
        halt("No value found on outgoing data port current_tempWstatus.\n                  Note: values placed during the initialization phase will persist across dispatches")
      case _ => return last_api_current_tempWstatus.get
    }
  }
  var preStateContainer_wL: Option[Temperature_Sensor_i_temperature_sensor_cpi_thermostat_PreState_Container_PS] = None()

  def pre_initialise(): Unit = {
    // assume/require contracts cannot refer to incoming ports or
    // state variables so nothing to do here
  }

  def post_initialise(): Unit = {
    // block the component while its post-state values are retrieved
    val postStateContainer_wL =
      Temperature_Sensor_i_temperature_sensor_cpi_thermostat_PostState_Container_PS(
        api_current_tempWstatus = get_api_current_tempWstatus)

    // the rest can now be performed via a different thread
    isolette.runtimemonitor.RuntimeMonitor.observeInitialisePostState(Arch.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat.id, isolette.runtimemonitor.ObservationKind.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat_postInit, postStateContainer_wL)
  }

  def pre_compute(): Unit = {
    // block the component while its pre-state values are retrieved
    preStateContainer_wL = Some(
      Temperature_Sensor_i_temperature_sensor_cpi_thermostat_PreState_Container_PS(
        api_air = Art.observeInPortVariable(Arch.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat.operational_api.air_Id).get.asInstanceOf[Isolette_Data_Model.PhysicalTemp_i_Payload].value))

    // the rest can now be performed via a different thread
    isolette.runtimemonitor.RuntimeMonitor.observeComputePreState(Arch.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat.id, isolette.runtimemonitor.ObservationKind.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat_preCompute, preStateContainer_wL.asInstanceOf[Option[art.DataContent]])
  }

  def post_compute(): Unit = {
    // block the component while its post-state values are retrieved
    val postStateContainer_wL =
      Temperature_Sensor_i_temperature_sensor_cpi_thermostat_PostState_Container_PS(
        api_current_tempWstatus = get_api_current_tempWstatus)

    // the rest can now be performed via a different thread
    isolette.runtimemonitor.RuntimeMonitor.observeComputePrePostState(Arch.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat.id, isolette.runtimemonitor.ObservationKind.Isolette_Single_Sensor_Instance_temperature_sensor_cpi_thermostat_postCompute, preStateContainer_wL.asInstanceOf[Option[art.DataContent]], postStateContainer_wL)
  }
}