package com.mayreh.kaiyote.backend

/**
 * Represents command result.
 */
case class CommandResult(
  stdout: String,
  stderr: String,
  exitStatus: Int)
