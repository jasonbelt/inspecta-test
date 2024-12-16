// #Sireum

package isolette.Operator_Interface

import org.sireum._
import art._
import isolette.SystemTestSuiteSlang.runtimeMonitorStream
import isolette._

// Do not edit this file as it will be overwritten if HAMR codegen is rerun

object Operator_Interface_Thread_i_operator_interface_oip_oit_SystemTestAPI {
  /** helper method to set the values of all incoming ports
    * @param api_alarm_control incoming data port
    * @param api_display_temperature incoming data port
    * @param api_monitor_status incoming data port
    * @param api_regulator_status incoming data port
    */
  def put_concrete_inputs(api_alarm_control: Isolette_Data_Model.On_Off.Type,
                          api_display_temperature: Isolette_Data_Model.Temp_i,
                          api_monitor_status: Isolette_Data_Model.Status.Type,
                          api_regulator_status: Isolette_Data_Model.Status.Type): Unit = {
    put_alarm_control(api_alarm_control)
    put_display_temperature(api_display_temperature)
    put_monitor_status(api_monitor_status)
    put_regulator_status(api_regulator_status)
  }

  // setter for incoming data port
  def put_alarm_control(value: Isolette_Data_Model.On_Off.Type): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.operational_api.alarm_control_Id, Isolette_Data_Model.On_Off_Payload(value))
  }

  // setter for incoming data port
  def put_display_temperature(value: Isolette_Data_Model.Temp_i): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.operational_api.display_temperature_Id, Isolette_Data_Model.Temp_i_Payload(value))
  }

  // setter for incoming data port
  def put_monitor_status(value: Isolette_Data_Model.Status.Type): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.operational_api.monitor_status_Id, Isolette_Data_Model.Status_Payload(value))
  }

  // setter for incoming data port
  def put_regulator_status(value: Isolette_Data_Model.Status.Type): Unit = {
    Art.insertInInfrastructurePort(Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.operational_api.regulator_status_Id, Isolette_Data_Model.Status_Payload(value))
  }

  def fetchContainer(): isolette.Operator_Interface.Operator_Interface_Thread_i_operator_interface_oip_oit_PostState_Container_PS = {
    if (runtimeMonitorStream.contains(Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.id)) {
      val (_, postContainer_) = runtimeMonitorStream.get(Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.id).get
      return postContainer_.asInstanceOf[isolette.Operator_Interface.Operator_Interface_Thread_i_operator_interface_oip_oit_PostState_Container_PS]
    }
    else {
      assert(F, s"No post state recorded for ${Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.name}")
      halt(s"No post state recorded for ${Arch.Isolette_Single_Sensor_Instance_operator_interface_oip_oit.name}")
    }
  }

  def check_concrete_outputs(api_lower_alarm_tempWstatus: Isolette_Data_Model.TempWstatus_i,
                             api_lower_desired_tempWstatus: Isolette_Data_Model.TempWstatus_i,
                             api_upper_alarm_tempWstatus: Isolette_Data_Model.TempWstatus_i,
                             api_upper_desired_tempWstatus: Isolette_Data_Model.TempWstatus_i): Unit = {
    var failureReasons: ISZ[ST] = ISZ()

    val actual_lower_alarm_tempWstatus = get_api_lower_alarm_tempWstatus()
    if (api_lower_alarm_tempWstatus != actual_lower_alarm_tempWstatus) {
      failureReasons = failureReasons :+ st"'lower_alarm_tempWstatus' did not match expected.  Expected: $api_lower_alarm_tempWstatus, Actual: $actual_lower_alarm_tempWstatus"
    }
    val actual_lower_desired_tempWstatus = get_api_lower_desired_tempWstatus()
    if (api_lower_desired_tempWstatus != actual_lower_desired_tempWstatus) {
      failureReasons = failureReasons :+ st"'lower_desired_tempWstatus' did not match expected.  Expected: $api_lower_desired_tempWstatus, Actual: $actual_lower_desired_tempWstatus"
    }
    val actual_upper_alarm_tempWstatus = get_api_upper_alarm_tempWstatus()
    if (api_upper_alarm_tempWstatus != actual_upper_alarm_tempWstatus) {
      failureReasons = failureReasons :+ st"'upper_alarm_tempWstatus' did not match expected.  Expected: $api_upper_alarm_tempWstatus, Actual: $actual_upper_alarm_tempWstatus"
    }
    val actual_upper_desired_tempWstatus = get_api_upper_desired_tempWstatus()
    if (api_upper_desired_tempWstatus != actual_upper_desired_tempWstatus) {
      failureReasons = failureReasons :+ st"'upper_desired_tempWstatus' did not match expected.  Expected: $api_upper_desired_tempWstatus, Actual: $actual_upper_desired_tempWstatus"
    }

    assert(failureReasons.isEmpty, st"${(failureReasons, "\n")}".render)
  }

  def get_api_lower_alarm_tempWstatus(): Isolette_Data_Model.TempWstatus_i = {
    return fetchContainer().api_lower_alarm_tempWstatus
  }

  def get_api_lower_desired_tempWstatus(): Isolette_Data_Model.TempWstatus_i = {
    return fetchContainer().api_lower_desired_tempWstatus
  }

  def get_api_upper_alarm_tempWstatus(): Isolette_Data_Model.TempWstatus_i = {
    return fetchContainer().api_upper_alarm_tempWstatus
  }

  def get_api_upper_desired_tempWstatus(): Isolette_Data_Model.TempWstatus_i = {
    return fetchContainer().api_upper_desired_tempWstatus
  }
}