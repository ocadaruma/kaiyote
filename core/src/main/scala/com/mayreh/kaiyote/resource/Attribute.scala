package com.mayreh.kaiyote.resource

sealed abstract class Attribute[A] {
  def value: A
}

object Attribute {
  case class AsIs[A](value: A) extends Attribute[A]
  case class Change[A](value: A) extends Attribute[A]
}
