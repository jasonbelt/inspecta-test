// #Sireum

package isolette.Monitor

import org.sireum._
import isolette._

// Do not edit this file as it will be overwritten if HAMR codegen is rerun
object Manage_Alarm_i_thermostat_mt_ma_ma_GumboX {
  /** Initialize Entrypoint Contract
    *
    * guarantee REQ_MA_1
    *   If the Monitor Mode is INIT, the Alarm Control shall be set
    *   to Off.
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=115 
    * @param lastCmd post-state state variable
    * @param api_alarm_control outgoing data port
    */
  @strictpure def initialize_REQ_MA_1 (
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    api_alarm_control == Isolette_Data_Model.On_Off.Off &
      lastCmd == Isolette_Data_Model.On_Off.Off

  /** IEP-Guar: Initialize Entrypoint Contracts for ma
    *
    * @param lastCmd post-state state variable
    * @param api_alarm_control outgoing data port
    */
  @strictpure def initialize_IEP_Guar (
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    initialize_REQ_MA_1(lastCmd, api_alarm_control)

  /** IEP-Post: Initialize Entrypoint Post-Condition
    *
    * @param lastCmd post-state state variable
    * @param api_alarm_control outgoing data port
    */
  @strictpure def inititialize_IEP_Post (
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (// IEP-Guar: Initialize Entrypoint contract for ma
     initialize_IEP_Guar(lastCmd, api_alarm_control))

  /** IEP-Post: Initialize Entrypoint Post-Condition via container
    *
    * @param post Container holding the value of incoming ports and the pre-state values of state variables
    */
  @strictpure def inititialize_IEP_Post_Container (post: Manage_Alarm_i_thermostat_mt_ma_ma_PostState_Container_PS): B =
    inititialize_IEP_Post (
      lastCmd = post.lastCmd,
      api_alarm_control = post.api_alarm_control)

  /** Compute Entrypoint Contract
    *
    * assumes Figure_A_7
    *   This is not explicitly stated in the requirements, but a reasonable
    *   assumption is that the lower alarm must be at least 1.0f less than
    *   the upper alarm in order to account for the 0.5f tolerance
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=115 
    * @param api_lower_alarm_temp incoming data port
    * @param api_upper_alarm_temp incoming data port
    */
  @strictpure def compute_spec_Figure_A_7_assume(
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i): B =
    api_upper_alarm_temp.degrees - api_lower_alarm_temp.degrees >= 1.0f

  /** Compute Entrypoint Contract
    *
    * assumes Table_A_12_LowerAlarmTemp
    *   Range [96..101]
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=112 
    * @param api_lower_alarm_temp incoming data port
    */
  @strictpure def compute_spec_Table_A_12_LowerAlarmTemp_assume(
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i): B =
    96.0f <= api_lower_alarm_temp.degrees &&
      api_lower_alarm_temp.degrees <= 101.0f

  /** Compute Entrypoint Contract
    *
    * assumes Table_A_12_UpperAlarmTemp
    *   Range [97..102]
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=112 
    * @param api_upper_alarm_temp incoming data port
    */
  @strictpure def compute_spec_Table_A_12_UpperAlarmTemp_assume(
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i): B =
    97.0f <= api_upper_alarm_temp.degrees &&
      api_upper_alarm_temp.degrees <= 102.0f

  /** CEP-T-Assm: Top-level assume contracts for ma's compute entrypoint
    *
    * @param api_lower_alarm_temp incoming data port
    * @param api_upper_alarm_temp incoming data port
    */
  @strictpure def compute_CEP_T_Assm (
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i): B =
    compute_spec_Figure_A_7_assume(api_lower_alarm_temp, api_upper_alarm_temp) &
    compute_spec_Table_A_12_LowerAlarmTemp_assume(api_lower_alarm_temp) &
    compute_spec_Table_A_12_UpperAlarmTemp_assume(api_upper_alarm_temp)

  /** CEP-Pre: Compute Entrypoint Pre-Condition for ma
    *
    * @param In_lastCmd pre-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    */
  @strictpure def compute_CEP_Pre (
      In_lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i): B =
    (// CEP-Assm: assume clauses of ma's compute entrypoint
     compute_CEP_T_Assm (api_lower_alarm_temp, api_upper_alarm_temp))

  /** CEP-Pre: Compute Entrypoint Pre-Condition for ma via container
    *
    * @param pre Container holding the value of incoming ports and the pre-state values of state variables
    */
  @strictpure def compute_CEP_Pre_Container(pre: Manage_Alarm_i_thermostat_mt_ma_ma_PreState_Container_PS): B =
    compute_CEP_Pre(
      In_lastCmd = pre.In_lastCmd,
      api_current_tempWstatus = pre.api_current_tempWstatus,
      api_lower_alarm_temp = pre.api_lower_alarm_temp,
      api_monitor_mode = pre.api_monitor_mode,
      api_upper_alarm_temp = pre.api_upper_alarm_temp)

  /** guarantee REQ_MA_1
    *   If the Monitor Mode is INIT, the Alarm Control shall be set
    *   to Off.
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=115 
    * @param lastCmd post-state state variable
    * @param api_monitor_mode incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_case_REQ_MA_1(
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (api_monitor_mode == Isolette_Data_Model.Monitor_Mode.Init_Monitor_Mode) -->:
      (api_alarm_control == Isolette_Data_Model.On_Off.Off &
         lastCmd == Isolette_Data_Model.On_Off.Off)

  /** guarantee REQ_MA_2
    *   If the Monitor Mode is NORMAL and the Current Temperature is
    *   less than the Lower Alarm Temperature or greater than the Upper Alarm
    *   Temperature, the Alarm Control shall be set to On.
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=115 
    * @param lastCmd post-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_case_REQ_MA_2(
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (api_monitor_mode == Isolette_Data_Model.Monitor_Mode.Normal_Monitor_Mode &
       (api_current_tempWstatus.degrees < api_lower_alarm_temp.degrees ||
         api_current_tempWstatus.degrees > api_upper_alarm_temp.degrees)) -->:
      (api_alarm_control == Isolette_Data_Model.On_Off.Onn &
         lastCmd == Isolette_Data_Model.On_Off.Onn)

  /** guarantee REQ_MA_3
    *   If the Monitor Mode is NORMAL and the Current Temperature
    *   is greater than or equal to the Lower Alarm Temperature and less than
    *   the Lower Alarm Temperature +0.5 degrees, or the Current Temperature is
    *   greater than the Upper Alarm Temperature -0.5 degrees and less than or equal
    *   to the Upper Alarm Temperature, the value of the Alarm Control shall
    *   not be changed.
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=115 
    * @param In_lastCmd pre-state state variable
    * @param lastCmd post-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_case_REQ_MA_3(
      In_lastCmd: Isolette_Data_Model.On_Off.Type,
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (api_monitor_mode == Isolette_Data_Model.Monitor_Mode.Normal_Monitor_Mode &
       (api_current_tempWstatus.degrees >= api_lower_alarm_temp.degrees &&
         api_current_tempWstatus.degrees < api_lower_alarm_temp.degrees + 0.5f ||
         api_current_tempWstatus.degrees > api_upper_alarm_temp.degrees - 0.5f &&
           api_current_tempWstatus.degrees <= api_upper_alarm_temp.degrees)) -->:
      (api_alarm_control == In_lastCmd &
         lastCmd == In_lastCmd)

  /** guarantee REQ_MA_4
    *   If the Monitor Mode is NORMAL and the value of the Current
    *   Temperature is greater than or equal to the Lower Alarm Temperature
    *   +0.5 degrees and less than or equal to the Upper Alarm Temperature
    *   -0.5 degrees, the Alarm Control shall be set to Off.
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=115 
    * @param lastCmd post-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_case_REQ_MA_4(
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (api_monitor_mode == Isolette_Data_Model.Monitor_Mode.Normal_Monitor_Mode &
       (api_current_tempWstatus.degrees >= api_lower_alarm_temp.degrees + 0.5f &
         api_current_tempWstatus.degrees <= api_upper_alarm_temp.degrees - 0.5f)) -->:
      (api_alarm_control == Isolette_Data_Model.On_Off.Off &
         lastCmd == Isolette_Data_Model.On_Off.Off)

  /** guarantee REQ_MA_5
    *   If the Monitor Mode is FAILED, the Alarm Control shall be
    *   set to On.
    *   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=116 
    * @param lastCmd post-state state variable
    * @param api_monitor_mode incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_case_REQ_MA_5(
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (api_monitor_mode == Isolette_Data_Model.Monitor_Mode.Failed_Monitor_Mode) -->:
      (api_alarm_control == Isolette_Data_Model.On_Off.Onn &
         lastCmd == Isolette_Data_Model.On_Off.Onn)

  /** CEP-T-Case: Top-Level case contracts for ma's compute entrypoint
    *
    * @param In_lastCmd pre-state state variable
    * @param lastCmd post-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_CEP_T_Case (
      In_lastCmd: Isolette_Data_Model.On_Off.Type,
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    compute_case_REQ_MA_1(lastCmd, api_monitor_mode, api_alarm_control) &
    compute_case_REQ_MA_2(lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp, api_alarm_control) &
    compute_case_REQ_MA_3(In_lastCmd, lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp, api_alarm_control) &
    compute_case_REQ_MA_4(lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp, api_alarm_control) &
    compute_case_REQ_MA_5(lastCmd, api_monitor_mode, api_alarm_control)

  /** CEP-Post: Compute Entrypoint Post-Condition for ma
    *
    * @param In_lastCmd pre-state state variable
    * @param lastCmd post-state state variable
    * @param api_current_tempWstatus incoming data port
    * @param api_lower_alarm_temp incoming data port
    * @param api_monitor_mode incoming data port
    * @param api_upper_alarm_temp incoming data port
    * @param api_alarm_control outgoing data port
    */
  @strictpure def compute_CEP_Post (
      In_lastCmd: Isolette_Data_Model.On_Off.Type,
      lastCmd: Isolette_Data_Model.On_Off.Type,
      api_current_tempWstatus: Isolette_Data_Model.TempWstatus_i,
      api_lower_alarm_temp: Isolette_Data_Model.Temp_i,
      api_monitor_mode: Isolette_Data_Model.Monitor_Mode.Type,
      api_upper_alarm_temp: Isolette_Data_Model.Temp_i,
      api_alarm_control: Isolette_Data_Model.On_Off.Type): B =
    (// CEP-T-Case: case clauses of ma's compute entrypoint
     compute_CEP_T_Case (In_lastCmd, lastCmd, api_current_tempWstatus, api_lower_alarm_temp, api_monitor_mode, api_upper_alarm_temp, api_alarm_control))

  /** CEP-Post: Compute Entrypoint Post-Condition for ma via containers
    *
    * @param pre Container holding the values of incoming ports and the pre-state values of state variables
    * @param post Container holding the values of outgoing ports and the post-state values of state variables
    */
  @strictpure def compute_CEP_Post_Container(
      pre: Manage_Alarm_i_thermostat_mt_ma_ma_PreState_Container_PS,
      post: Manage_Alarm_i_thermostat_mt_ma_ma_PostState_Container_PS): B =
    compute_CEP_Post(
      In_lastCmd = pre.In_lastCmd,
      lastCmd = post.lastCmd,
      api_current_tempWstatus = pre.api_current_tempWstatus,
      api_lower_alarm_temp = pre.api_lower_alarm_temp,
      api_monitor_mode = pre.api_monitor_mode,
      api_upper_alarm_temp = pre.api_upper_alarm_temp,
      api_alarm_control = post.api_alarm_control)
}
