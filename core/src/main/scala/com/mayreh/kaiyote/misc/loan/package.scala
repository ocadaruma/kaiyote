package com.mayreh.kaiyote.misc

package object loan {
  def using[A : Disposer, B](a: A)(op: A => B): B = try {
    op(a)
  } finally {
    implicitly[Disposer[A]].dispose(a)
  }
}
