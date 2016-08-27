package com.mayreh.kaiyote

import scopt.OptionParser

abstract class Root {

  def rootConfiguration: Configuration

  def main(rawArgs: Array[String]): Unit = {
    ArgumentsParser.parse(rawArgs, Arguments()) match {
      case None =>
      case Some(args) =>
    }
  }
}
