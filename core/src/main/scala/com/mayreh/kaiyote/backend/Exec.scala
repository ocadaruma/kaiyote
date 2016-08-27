package com.mayreh.kaiyote.backend

import scala.sys.process.{Process, ProcessLogger}

/**
 * Provides features to execute commands.
 */
trait Exec extends Backend {
  def runCommand(cmd: Command): CommandResult = {
    val builder = Process(cmd.command)

    val stdoutLog = StringBuilder.newBuilder
    val stderrLog = StringBuilder.newBuilder

    val processLogger = ProcessLogger(stdoutLog ++= _, stderrLog ++= _)

    val status = builder.run(processLogger).exitValue()

    CommandResult(
      stdoutLog.result(),
      stderrLog.result(),
      status
    )
  }
}
