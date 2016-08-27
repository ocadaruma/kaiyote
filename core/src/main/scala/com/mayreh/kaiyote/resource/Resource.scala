package com.mayreh.kaiyote.resource

import com.mayreh.kaiyote.backend.{CommandResult, Command, Backend}

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
    def shouldSkipBecauseOnlyIf(): Boolean = onlyIf.fold(false)(runCommand(backend, _).exitStatus != 0)
    def shouldSkipBecauseNotIf(): Boolean = notIf.fold(false)(runCommand(backend, _).exitStatus == 0)

    if (shouldSkipBecauseOnlyIf()) {
      // LOG
    } else if (shouldSkipBecauseNotIf()) {
      // LOG
    } else {
      runAction(backend)
    }
  }

  protected def runCommand(backend: Backend, cmd: Command): CommandResult = backend.runCommand(cmd)

  private[this] def runAction(backend: Backend): Unit = {
    actions.foreach(runSingleAction(backend, _))
  }
}
