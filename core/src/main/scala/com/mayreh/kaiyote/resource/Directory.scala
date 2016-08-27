package com.mayreh.kaiyote.resource

import java.io.File

import com.mayreh.kaiyote.backend.{Backend, Command}
import com.mayreh.kaiyote.data.FilePermission

sealed abstract class DirectoryAction extends Action
object DirectoryAction {
  case object Ignore extends DirectoryAction
  case object Create extends DirectoryAction
  case object Delete extends DirectoryAction
}

case class DirectoryAttributes(
  exists: Boolean,
  mode: Option[FilePermission] = None,
  owner: Option[String] = None,
  group: Option[String] = None
)

/**
 * Represents resource that manipulates directory.
 */
case class Directory(
  path: File,
  actions: List[DirectoryAction] = List(DirectoryAction.Create),
  onlyIf: Option[Command] = None,
  notIf: Option[Command] = None
) extends Resource {

  type ActionType = DirectoryAction

  def runSingleAction(backend: Backend, action: DirectoryAction): Unit = action match {
    case DirectoryAction.Ignore => // do nothing
    case DirectoryAction.Create => println("to be implemented")
    case DirectoryAction.Delete => println("to be implemented")
  }

  //========================
  // for fluent interface
  //========================
  def action(action: DirectoryAction): Directory = copy(actions = List(action))
  def actions(actions: List[DirectoryAction]): Directory = copy(actions = actions)
  def onlyIf(command: String): Directory = copy(onlyIf = Some(Command(command)))
  def notIf(command: String): Directory = copy(notIf = Some(Command(command)))
}
