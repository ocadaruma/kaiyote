package com.mayreh.kaiyote.logger

trait Loggable {
  def withIndent[A](f: => A): A = {
    f
  }
}
