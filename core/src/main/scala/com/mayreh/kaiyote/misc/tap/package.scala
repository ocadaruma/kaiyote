package com.mayreh.kaiyote.misc

package object tap {
  implicit class AnyTap[A](val self: A) extends AnyVal {
    def tap(f: A => Unit): A = {
      f(self)
      self
    }

    def tapMatch(f: PartialFunction[A, Unit]): A = {
      if (f.isDefinedAt(self)) f(self)
      self
    }
  }

  implicit class BooleanTap(val self: Boolean) extends AnyVal {
    def tapTrue(f: => Unit): Boolean = {
      if (self) f
      self
    }

    def tapFalse(f: => Unit): Boolean = {
      if (!self) f
      self
    }
  }
}
