package com.mayreh.kaiyote.backend

/**
 * Represents backend to run configurations.
 */
trait Backend extends AutoCloseable {
  def runCommand(cmd: Command): CommandResult
}
