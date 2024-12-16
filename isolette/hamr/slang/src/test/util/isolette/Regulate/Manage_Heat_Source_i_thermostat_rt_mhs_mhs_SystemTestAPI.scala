// #Sireum

package isolette.Regulate

import org.sireum._
import art._
import isolette.SystemTestSuiteSlang.runtimeMonitorStream
import isolette._

// Do not edit this file as it will be overwritten if HAMR codegen is rerun

object Manage_Heat_Source_i_thermostat_rt_mhs_mhs_SystemTestAPI {
  /** helper method to set the values of all incoming ports and state variables
    * @param In_lastCmd pre-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_desired_temp incoming data port
    * @param api_regulator_mode incoming data port
    * @param api_upper_desired_temp incoming data port
    */
  def put_concrete_inputs(In_lastCmd: Isolette_Data_Model.On_Off.Type,
                          api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
                          api_lower_desired_temp: Isolette_Data_Model.Temp_i,
                          api_regulator_mode: Isolette_Data_Model.Regulator_Mode.Type,
                          api_upper_desired_temp: Isolette_Data_Model.Temp_i): Unit = {
    put_In_lastCmd(In_lastCmd)
    put_current_tempWstatus(api_current_tempWstatus)
    put_lower_desired_temp(api_lower_desired_temp)
    put_regulator_mode(api_regulator_mode)
    put_upper_desired_temp(api_upper_desired_temp)
  }

  // setter for state variable
  def put_In_lastCmd(value: Isolette_Data_Model.On_Off.Type): Unit = {
    Manage_Heat_Source_i_thermostat_rt_mhs_mhs.lastCmd = value
  }

  // setter for incoming data port
  def put_current_tempWstatus(value: Isolette_Data_Model.TempWstatus_i): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.operational_api.current_tempWstatus_Id, Isolette_Data_Model.TempWstatus_i_Payload(value))
  }

  // setter for incoming data port
  def put_lower_desired_temp(value: Isolette_Data_Model.Temp_i): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.operational_api.lower_desired_temp_Id, Isolette_Data_Model.Temp_i_Payload(value))
  }

  // setter for incoming data port
  def put_regulator_mode(value: Isolette_Data_Model.Regulator_Mode.Type): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.operational_api.regulator_mode_Id, Isolette_Data_Model.Regulator_Mode_Payload(value))
  }

  // setter for incoming data port
  def put_upper_desired_temp(value: Isolette_Data_Model.Temp_i): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.operational_api.upper_desired_temp_Id, Isolette_Data_Model.Temp_i_Payload(value))
  }

  def fetchContainer(): isolette.Regulate.Manage_Heat_Source_i_thermostat_rt_mhs_mhs_PostState_Container_PS = {
    if (runtimeMonitorStream.contains(Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.id)) {
      val (_, postContainer_) = runtimeMonitorStream.get(Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.id).get
      return postContainer_.asInstanceOf[isolette.Regulate.Manage_Heat_Source_i_thermostat_rt_mhs_mhs_PostState_Container_PS]
    }
    else {
      assert(F, s"No post state recorded for ${Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.name}")
      halt(s"No post state recorded for ${Arch.Isolette_Single_Sensor_Instance_thermostat_rt_mhs_mhs.name}")
    }
  }

  def check_concrete_outputs(lastCmd: Isolette_Data_Model.On_Off.Type,
                             api_heat_control: Isolette_Data_Model.On_Off.Type): Unit = {
    var failureReasons: ISZ[ST] = ISZ()

    val actual_lastCmd = get_lastCmd()
    if (lastCmd != actual_lastCmd) {
      failureReasons = failureReasons :+ st"'lastCmd' did not match expected.  Expected: $lastCmd, Actual: $actual_lastCmd"
    }
    val actual_heat_control = get_api_heat_control()
    if (api_heat_control != actual_heat_control) {
      failureReasons = failureReasons :+ st"'heat_control' did not match expected.  Expected: $api_heat_control, Actual: $actual_heat_control"
    }

    assert(failureReasons.isEmpty, st"${(failureReasons, "\n")}".render)
  }

  // getter for state variable
  def get_lastCmd(): Isolette_Data_Model.On_Off.Type = {
    return Manage_Heat_Source_i_thermostat_rt_mhs_mhs.lastCmd
  }

  def get_api_heat_control(): Isolette_Data_Model.On_Off.Type = {
    return fetchContainer().api_heat_control
  }
}