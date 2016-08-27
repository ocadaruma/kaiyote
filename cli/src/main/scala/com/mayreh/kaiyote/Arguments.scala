package com.mayreh.kaiyote

import scopt.OptionParser

sealed abstract class Command
object Command {
  case object Local extends Command
  case class Help(helpOption: HelpOption) extends Command
}

case class HelpOption(targetCommand: Helps.Command)

case class Arguments(
  command: Option[Command] = None)

object ArgumentsParser extends OptionParser[Arguments]("Kaiyote") {
  override val showUsageOnError = false
  override val errorOnUnknownArgument = false

  cmd("local").action { (_, c) =>
    c.copy(command = Some(Command.Local))
  }

  cmd("help").children(
    cmd("local").action { (_, c) =>
      c.copy(command = Some(Command.Help(HelpOption(Helps.Command.Local))))
    },
    cmd("help").action { (_, c) =>
      c.copy(command = Some(Command.Help(HelpOption(Helps.Command.Help))))
    }
  )
}
