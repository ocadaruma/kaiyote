package com.mayreh.kaiyote

import com.mayreh.kaiyote.data.BackendType
import scopt.OptionParser

case class Arguments(
  backendType: Option[BackendType] = None)

object ArgumentsParser extends OptionParser[Arguments]("Kaiyote") {
  cmd("local").action { (_, c) =>
    c.copy(backendType = Some(BackendType.Local))
  }
}
