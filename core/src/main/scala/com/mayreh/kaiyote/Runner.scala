package com.mayreh.kaiyote

import com.mayreh.kaiyote.backend.{Backend, BackendType, Local}
import com.mayreh.kaiyote.resource.Resource

class Runner(backend: Backend) {
  def run(resources: Vector[Resource]): Unit = {
    resources.foreach { resource =>
      resource.run(backend)
    }
  }
}

object Runner {
  def apply(backendType: BackendType): Runner = backendType match {
    case BackendType.Local => new Runner(new Local)
  }
}
