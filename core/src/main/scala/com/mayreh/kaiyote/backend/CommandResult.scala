package com.mayreh.kaiyote.backend

/**
 * Represents command result.
 */
case class CommandResult(
  stdout: Option[String],
  stderr: Option[String],
  exitStatus: Int)
