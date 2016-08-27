package com.example

import com.mayreh.kaiyote.dsl._
import com.mayreh.kaiyote.Root

object Example extends Root {
  val rootConfiguration =
    directory("example") ++
      execute("create empty file").command("touch empty")
}
