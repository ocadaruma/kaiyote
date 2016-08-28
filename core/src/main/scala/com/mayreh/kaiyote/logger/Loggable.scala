package com.mayreh.kaiyote.logger

/**
 * Provides logging feature.
 */
trait Loggable {
  protected lazy val logger = Logger(this.getClass)
}
