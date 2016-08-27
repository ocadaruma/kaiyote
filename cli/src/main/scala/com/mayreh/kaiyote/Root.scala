package com.mayreh.kaiyote

import com.mayreh.kaiyote.backend.BackendType

abstract class Root {

  def rootConfiguration: Configuration

  def main(rawArgs: Array[String]): Unit = {
    ArgumentsParser.parse(rawArgs, Arguments()) match {
      case Some(Arguments(Some(command))) => command match {
        // show help
        case Command.Help(helpOption) => println(helpOption.targetCommand.helpText)

        // run locally
        case Command.Local =>
          val runner = Runner(BackendType.Local)
          runner.run(rootConfiguration.resources)
      }
      case _ => println(Helps.default)
    }
  }
}
