package com.mayreh.kaiyote.misc.loan

trait Disposer[-A] {
  def dispose(a: A): Unit
}

object Disposer {
  implicit val autoCloseable: Disposer[AutoCloseable] = new Disposer[AutoCloseable] {
    def dispose(a: AutoCloseable): Unit = a.close()
  }
}
