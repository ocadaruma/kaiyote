package com.mayreh.kaiyote

object Helps {
  val default =
    """Commands:
      |  help [COMMAND]                   # Describe available commands or one specific command
      |  local RECIPE [RECIPE...]         # Run Kaiyote locally
      |  version                          # Print version
    """.stripMargin

  val local =
    """Usage:
      |  local
      |
      |Run Kaiyote locally""".stripMargin

  val help =
    """Usage:
      |  help [COMMAND]
      |
      |Describe available commands or one specific command"""

  sealed abstract class Command(val helpText: String)
  object Command {
    case object Local extends Command(local)
    case object Help extends Command(help)
  }
}
