package com.mayreh.kaiyote.resource

import com.mayreh.kaiyote.backend.{Backend, Command}

/**
 * Represents resource that executes command.
 */
case class Execute(
  name: String,
  command: String,
  onlyIf: Option[Command] = None,
  notIf: Option[Command] = None
) extends Resource {

  type ActionType = ExecuteAction

  def runAction(backend: Backend, action: ExecuteAction): Unit = action match {
    case ExecuteAction.Ignore => // do nothing
    case ExecuteAction.Run => runCommand(backend, Command(command))
  }

  //========================
  // for fluent interface
  //========================
  def action(action: ExecuteAction): Execute = copy(actions = List(action))
  def actions(actions: List[ExecuteAction]): Execute = copy(actions = actions)
  def onlyIf(command: String): Execute = copy(onlyIf = Some(Command(command)))
  def notIf(command: String): Execute = copy(notIf = Some(Command(command)))
}
