// #Sireum

package isolette.Monitor

import org.sireum._
import art._
import isolette._

object Manage_Monitor_Interface_i_Api {
  // assume Table_A_12_UpperAlarmTemp
  //   Range [97..102]
  //   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=112 
  @strictpure def Table_A_12_UpperAlarmTemp(upper_alarm_tempWstatus: Isolette_Data_Model.TempWstatus_i): B =
    97.0f <= upper_alarm_tempWstatus.degrees &&
      upper_alarm_tempWstatus.degrees <= 102.0f

  // assume Table_A_12_LowerAlarmTemp
  //   Range [96..101]
  //   http://pub.santoslab.org/high-assurance/module-requirements/reading/FAA-DoT-Requirements-AR-08-32.pdf#page=112 
  @strictpure def Table_A_12_LowerAlarmTemp(lower_alarm_tempWstatus: Isolette_Data_Model.TempWstatus_i): B =
    96.0f <= lower_alarm_tempWstatus.degrees &&
      lower_alarm_tempWstatus.degrees <= 101.0f
}

import Manage_Monitor_Interface_i_Api._

@sig trait Manage_Monitor_Interface_i_Api {
  def id: Art.BridgeId
  def upper_alarm_tempWstatus_Id : Art.PortId
  def lower_alarm_tempWstatus_Id : Art.PortId
  def current_tempWstatus_Id : Art.PortId
  def monitor_mode_Id : Art.PortId
  def upper_alarm_temp_Id : Art.PortId
  def lower_alarm_temp_Id : Art.PortId
  def monitor_status_Id : Art.PortId
  def interface_failure_Id : Art.PortId

  // Logika spec var representing port state for outgoing data port
  @spec var upper_alarm_temp: Isolette_Data_Model.Temp_i = $

  def put_upper_alarm_temp(value : Isolette_Data_Model.Temp_i) : Unit = {
    Contract(
      Modifies(upper_alarm_temp),
      Ensures(
        upper_alarm_temp == value
      )
    )
    Spec {
      upper_alarm_temp = value
    }

    Art.putValue(upper_alarm_temp_Id, Isolette_Data_Model.Temp_i_Payload(value))
  }

  // Logika spec var representing port state for outgoing data port
  @spec var lower_alarm_temp: Isolette_Data_Model.Temp_i = $

  def put_lower_alarm_temp(value : Isolette_Data_Model.Temp_i) : Unit = {
    Contract(
      Modifies(lower_alarm_temp),
      Ensures(
        lower_alarm_temp == value
      )
    )
    Spec {
      lower_alarm_temp = value
    }

    Art.putValue(lower_alarm_temp_Id, Isolette_Data_Model.Temp_i_Payload(value))
  }

  // Logika spec var representing port state for outgoing data port
  @spec var monitor_status: Isolette_Data_Model.Status.Type = $

  def put_monitor_status(value : Isolette_Data_Model.Status.Type) : Unit = {
    Contract(
      Modifies(monitor_status),
      Ensures(
        monitor_status == value
      )
    )
    Spec {
      monitor_status = value
    }

    Art.putValue(monitor_status_Id, Isolette_Data_Model.Status_Payload(value))
  }

  // Logika spec var representing port state for outgoing data port
  @spec var interface_failure: Isolette_Data_Model.Failure_Flag_i = $

  def put_interface_failure(value : Isolette_Data_Model.Failure_Flag_i) : Unit = {
    Contract(
      Modifies(interface_failure),
      Ensures(
        interface_failure == value
      )
    )
    Spec {
      interface_failure = value
    }

    Art.putValue(interface_failure_Id, Isolette_Data_Model.Failure_Flag_i_Payload(value))
  }

  def logInfo(msg: String): Unit = {
    Art.logInfo(id, msg)
  }

  def logDebug(msg: String): Unit = {
    Art.logDebug(id, msg)
  }

  def logError(msg: String): Unit = {
    Art.logError(id, msg)
  }
}

@datatype class Manage_Monitor_Interface_i_Initialization_Api (
  val id: Art.BridgeId,
  val upper_alarm_tempWstatus_Id : Art.PortId,
  val lower_alarm_tempWstatus_Id : Art.PortId,
  val current_tempWstatus_Id : Art.PortId,
  val monitor_mode_Id : Art.PortId,
  val upper_alarm_temp_Id : Art.PortId,
  val lower_alarm_temp_Id : Art.PortId,
  val monitor_status_Id : Art.PortId,
  val interface_failure_Id : Art.PortId) extends Manage_Monitor_Interface_i_Api

@datatype class Manage_Monitor_Interface_i_Operational_Api (
  val id: Art.BridgeId,
  val upper_alarm_tempWstatus_Id : Art.PortId,
  val lower_alarm_tempWstatus_Id : Art.PortId,
  val current_tempWstatus_Id : Art.PortId,
  val monitor_mode_Id : Art.PortId,
  val upper_alarm_temp_Id : Art.PortId,
  val lower_alarm_temp_Id : Art.PortId,
  val monitor_status_Id : Art.PortId,
  val interface_failure_Id : Art.PortId) extends Manage_Monitor_Interface_i_Api {

  // Logika spec var representing port state for incoming data port
  @spec var upper_alarm_tempWstatus: Isolette_Data_Model.TempWstatus_i = $
  @spec def upper_alarm_tempWstatus_Inv = Invariant(
    Table_A_12_UpperAlarmTemp(upper_alarm_tempWstatus)
  )

  def get_upper_alarm_tempWstatus() : Option[Isolette_Data_Model.TempWstatus_i] = {
    Contract(
      Ensures(
        Table_A_12_UpperAlarmTemp(upper_alarm_tempWstatus),
        Res == Some(upper_alarm_tempWstatus)
      )
    )
    val value : Option[Isolette_Data_Model.TempWstatus_i] = Art.getValue(upper_alarm_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_i_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port upper_alarm_tempWstatus.  Expecting 'Isolette_Data_Model.TempWstatus_i_Payload' but received ${v}")
        None[Isolette_Data_Model.TempWstatus_i]()
      case _ => None[Isolette_Data_Model.TempWstatus_i]()
    }
    return value
  }

  // Logika spec var representing port state for incoming data port
  @spec var lower_alarm_tempWstatus: Isolette_Data_Model.TempWstatus_i = $
  @spec def lower_alarm_tempWstatus_Inv = Invariant(
    Table_A_12_LowerAlarmTemp(lower_alarm_tempWstatus)
  )

  def get_lower_alarm_tempWstatus() : Option[Isolette_Data_Model.TempWstatus_i] = {
    Contract(
      Ensures(
        Table_A_12_LowerAlarmTemp(lower_alarm_tempWstatus),
        Res == Some(lower_alarm_tempWstatus)
      )
    )
    val value : Option[Isolette_Data_Model.TempWstatus_i] = Art.getValue(lower_alarm_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_i_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port lower_alarm_tempWstatus.  Expecting 'Isolette_Data_Model.TempWstatus_i_Payload' but received ${v}")
        None[Isolette_Data_Model.TempWstatus_i]()
      case _ => None[Isolette_Data_Model.TempWstatus_i]()
    }
    return value
  }

  // Logika spec var representing port state for incoming data port
  @spec var current_tempWstatus: Isolette_Data_Model.TempWstatus_i = $

  def get_current_tempWstatus() : Option[Isolette_Data_Model.TempWstatus_i] = {
    Contract(
      Ensures(
        Res == Some(current_tempWstatus)
      )
    )
    val value : Option[Isolette_Data_Model.TempWstatus_i] = Art.getValue(current_tempWstatus_Id) match {
      case Some(Isolette_Data_Model.TempWstatus_i_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port current_tempWstatus.  Expecting 'Isolette_Data_Model.TempWstatus_i_Payload' but received ${v}")
        None[Isolette_Data_Model.TempWstatus_i]()
      case _ => None[Isolette_Data_Model.TempWstatus_i]()
    }
    return value
  }

  // Logika spec var representing port state for incoming data port
  @spec var monitor_mode: Isolette_Data_Model.Monitor_Mode.Type = $

  def get_monitor_mode() : Option[Isolette_Data_Model.Monitor_Mode.Type] = {
    Contract(
      Ensures(
        Res == Some(monitor_mode)
      )
    )
    val value : Option[Isolette_Data_Model.Monitor_Mode.Type] = Art.getValue(monitor_mode_Id) match {
      case Some(Isolette_Data_Model.Monitor_Mode_Payload(v)) => Some(v)
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port monitor_mode.  Expecting 'Isolette_Data_Model.Monitor_Mode_Payload' but received ${v}")
        None[Isolette_Data_Model.Monitor_Mode.Type]()
      case _ => None[Isolette_Data_Model.Monitor_Mode.Type]()
    }
    return value
  }
}
