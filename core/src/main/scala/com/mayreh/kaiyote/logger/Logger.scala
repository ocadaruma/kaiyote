package com.mayreh.kaiyote.logger

import org.slf4j.{Logger => Slf4jLogger, LoggerFactory}

import scala.io.AnsiColor

sealed abstract class LogColor(val ansiColor: String) {
  def colorize(msg: String): String = s"$ansiColor$msg${AnsiColor.RESET}"
}

object LogColor {
  case object Red extends LogColor(AnsiColor.RED)
  case object Green extends LogColor(AnsiColor.GREEN)
  case object Magenta extends LogColor(AnsiColor.MAGENTA)
  case object Cyan extends LogColor(AnsiColor.CYAN)
  case object Blue extends LogColor(AnsiColor.BLUE)
  case object Yellow extends LogColor(AnsiColor.YELLOW)
}

case class LoggerContext(indent: Int = 0) {
  def indentDown = LoggerContext(indent = indent + 4)
}

object Logger {
  def apply(klass: Class[_]): Logger = new DefaultLogger(LoggerFactory.getLogger(klass), LoggerContext())
}

trait Logger {

  protected def slf4jLogger: Slf4jLogger

  protected def context: LoggerContext

  private[this] def shapeMessage(msg: String): String = (1 to context.indent).map(_ => ' ').mkString + msg

  //========================
  // error
  //========================
  def error(msg: => String): Unit = {
    if (slf4jLogger.isErrorEnabled) slf4jLogger.error(shapeMessage(msg))
  }

  def error(msg: => String, color: LogColor): Unit = {
    if (slf4jLogger.isErrorEnabled) slf4jLogger.error(color.colorize(shapeMessage(msg)))
  }

  def error(msg: => String, t: Throwable): Unit = {
    if (slf4jLogger.isErrorEnabled) slf4jLogger.error(shapeMessage(msg), t)
  }

  def error(msg: => String, t: Throwable, color: LogColor): Unit = {
    if (slf4jLogger.isErrorEnabled) slf4jLogger.error(color.colorize(shapeMessage(msg)), t)
  }

  //========================
  // warn
  //========================
  def warn(msg: => String): Unit = {
    if (slf4jLogger.isWarnEnabled) slf4jLogger.warn(shapeMessage(msg))
  }

  def warn(msg: => String, color: LogColor): Unit = {
    if (slf4jLogger.isWarnEnabled) slf4jLogger.warn(color.colorize(shapeMessage(msg)))
  }

  def warn(msg: => String, t: Throwable): Unit = {
    if (slf4jLogger.isWarnEnabled) slf4jLogger.warn(shapeMessage(msg), t)
  }

  def warn(msg: => String, t: Throwable, color: LogColor): Unit = {
    if (slf4jLogger.isWarnEnabled) slf4jLogger.warn(color.colorize(shapeMessage(msg)), t)
  }

  //========================
  // info
  //========================
  def info(msg: => String): Unit = {
    if (slf4jLogger.isInfoEnabled) slf4jLogger.info(shapeMessage(msg))
  }

  def info(msg: => String, color: LogColor): Unit = {
    if (slf4jLogger.isInfoEnabled) slf4jLogger.info(color.colorize(shapeMessage(msg)))
  }

  def info(msg: => String, t: Throwable): Unit = {
    if (slf4jLogger.isInfoEnabled) slf4jLogger.info(shapeMessage(msg), t)
  }

  def info(msg: => String, t: Throwable, color: LogColor): Unit = {
    if (slf4jLogger.isInfoEnabled) slf4jLogger.info(color.colorize(shapeMessage(msg)), t)
  }

  //========================
  // debug
  //========================
  def debug(msg: => String): Unit = {
    if (slf4jLogger.isDebugEnabled) slf4jLogger.debug(shapeMessage(msg))
  }

  def debug(msg: => String, color: LogColor): Unit = {
    if (slf4jLogger.isDebugEnabled) slf4jLogger.debug(color.colorize(shapeMessage(msg)))
  }

  def debug(msg: => String, t: Throwable): Unit = {
    if (slf4jLogger.isDebugEnabled) slf4jLogger.debug(shapeMessage(msg), t)
  }

  def debug(msg: => String, t: Throwable, color: LogColor): Unit = {
    if (slf4jLogger.isDebugEnabled) slf4jLogger.debug(color.colorize(shapeMessage(msg)), t)
  }

  //========================
  // trace
  //========================
  def trace(msg: => String): Unit = {
    if (slf4jLogger.isTraceEnabled) slf4jLogger.trace(shapeMessage(msg))
  }

  def trace(msg: => String, color: LogColor): Unit = {
    if (slf4jLogger.isTraceEnabled) slf4jLogger.trace(color.colorize(shapeMessage(msg)))
  }

  def trace(msg: => String, t: Throwable): Unit = {
    if (slf4jLogger.isTraceEnabled) slf4jLogger.trace(shapeMessage(msg), t)
  }

  def trace(msg: => String, t: Throwable, color: LogColor): Unit = {
    if (slf4jLogger.isTraceEnabled) slf4jLogger.trace(color.colorize(shapeMessage(msg)), t)
  }

  //========================
  // indentation
  //========================
  def withIndent[A](f: Logger => A): A = {
    val rootLogger = slf4jLogger
    val currentContext = context

    val indentedLogger = new Logger {
      protected def slf4jLogger = rootLogger

      override protected val context = currentContext.indentDown
    }

    f(indentedLogger)
  }
}

class DefaultLogger(
  protected val slf4jLogger: Slf4jLogger,
  protected val context: LoggerContext
) extends Logger
