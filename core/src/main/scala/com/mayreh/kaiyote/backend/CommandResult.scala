package com.mayreh.kaiyote.backend

import com.mayreh.kaiyote.data.{Stdout, Stderr}

/**
 * Represents command result.
 */
case class CommandResult(
  stdout: Stdout,
  stderr: Stderr,
  exitStatus: Int)
