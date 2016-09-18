package com.mayreh.kaiyote.resource

import java.io.File

import com.mayreh.kaiyote.backend.Backend
import com.mayreh.kaiyote.data.{FileGroup, FileOwner, FilePermission}

case class DirectoryState(
  exists: Boolean,
  mode: Option[FilePermission] = None,
  owner: Option[FileOwner] = None,
  group: Option[FileGroup] = None
) extends State {

  def describe: String = {

  }
}

/**
 * Represents resource that manipulates directory.
 */
case class Directory(
  name: String,
  path: File,
  to: DirectoryState
) extends Resource[DirectoryState] {

  def current(backend: Backend): DirectoryState = {
    val exists = backend.isDirectory(path)

    DirectoryState(
      exists = exists,
      mode = if (exists) Some(backend.getFileMode(path)) else None,
      owner = if (exists) Some(backend.getFileOwner(path)) else None,
      group = if (exists) Some(backend.getFileGroup(path)) else None
    )
  }

  def run(backend: Backend): Unit = {
    if (to.exists) {
      if (!current(backend).exists) {
        backend.createFileAsDirectory(path)
      }

      to.mode.foreach(backend.changeFileMode(path, _))
      to.owner.foreach(backend.changeFileOwner(path, _))
      to.group.foreach(backend.changeFileGroup(path, _))
    } else {
      if (current(backend).exists) {
        backend.removeFile(path)
      }
    }
  }
}
