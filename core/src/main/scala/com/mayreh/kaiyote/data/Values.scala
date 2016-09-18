package com.mayreh.kaiyote.data

case class Stdout(value: String) extends AnyVal

case class Stderr(value: String) extends AnyVal

case class FileGroup(value: String) extends AnyVal

case class FileOwner(value: String) extends AnyVal
