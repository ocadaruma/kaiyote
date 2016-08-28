package com.mayreh.kaiyote.resource

import com.mayreh.kaiyote.backend.{CommandResult, Command, Backend}
import com.mayreh.kaiyote.misc.tap._

trait Resource {
  type ActionType <: Action

  //========================
  // abstract members
  //========================
  def actions: List[ActionType]

  def onlyIf: Option[Command]

  def notIf: Option[Command]

  def runSingleAction(backend: Backend, action: ActionType): Unit

  def run(backend: Backend): Unit = {
    def shouldSkipBecauseOnlyIf(): Boolean = onlyIf.fold(false)(runCommand(backend, _).exitStatus != 0).tapTrue {
      // TODO LOG
      println("Skipped because command failed.")
    }
    def shouldSkipBecauseNotIf(): Boolean = notIf.fold(false)(runCommand(backend, _).exitStatus == 0).tapTrue {
      // TODO LOG
      println("Skipped because command succeeded.")
    }

    if (!shouldSkipBecauseOnlyIf() && !shouldSkipBecauseNotIf()) {
      runAction(backend)
    }
  }

  protected def runCommand(backend: Backend, cmd: Command): CommandResult = backend.runCommand(cmd)

  protected def runAction(backend: Backend): Unit = {
    actions.foreach(runSingleAction(backend, _))
  }

  protected def showDifferences(): Unit = {

  }
}
