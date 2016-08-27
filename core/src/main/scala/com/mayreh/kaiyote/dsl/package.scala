package com.mayreh.kaiyote

import java.io.File

import com.mayreh.kaiyote.resource.{Resource, Directory, Execute}

import scala.language.implicitConversions

/**
 * Provides DSL to define resources.
 */
package object dsl extends ExecuteDSL with DirectoryDSL {
  implicit def resourceToConfiguration(resource: Resource): Configuration =
    Configuration(Vector(resource))
}

trait ExecuteDSL {
  def execute(name: String): ExecuteBuilder = new ExecuteBuilder(name)

  class ExecuteBuilder(name: String) {
    def command(command: String): Execute = Execute(name, command)
  }
}

trait DirectoryDSL {
  def directory(path: String): Directory = Directory(new File(path))
  def directory(path: File): Directory = Directory(path)
}
