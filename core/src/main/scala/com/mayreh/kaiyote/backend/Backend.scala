package com.mayreh.kaiyote.backend

/**
 * Represents backend to run configurations.
 */
trait Backend {
  def runCommand(cmd: Command): CommandResult
}
