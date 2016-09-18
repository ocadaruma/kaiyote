package com.mayreh.kaiyote.backend

import java.io.File
import java.nio.file.{FileSystems, Files}
import java.nio.file.attribute.{PosixFileAttributeView, PosixFileAttributes}

import com.mayreh.kaiyote.data.{FileOwner, FileGroup, FilePermission}
import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

/**
 * Represents local backend.
 */
class Local extends Exec {

  def close(): Unit = {
    // do nothing
  }

  def isDirectory(path: File): Boolean = {
    path.isDirectory
  }

  def createFileAsDirectory(path: File): Unit = {
    Files.createDirectories(path.toPath)
  }

  def getFileMode(path: File): FilePermission = {
    val permissions = Files.getPosixFilePermissions(path.toPath).asScala

    FilePermission(permissions.toSet)
  }

  def changeFileMode(path: File, mode: FilePermission): Unit = {
    Files.setPosixFilePermissions(path.toPath, mode.toPosixFilePermission.asJava)
  }

  def getFileGroup(path: File): FileGroup = {
    FileGroup(Files.readAttributes(path.toPath, classOf[PosixFileAttributes]).group().getName)
  }

  def changeFileGroup(path: File, group: FileGroup): Unit = {
    val service = FileSystems.getDefault.getUserPrincipalLookupService

    Files.getFileAttributeView(path.toPath, classOf[PosixFileAttributeView])
      .setGroup(service.lookupPrincipalByGroupName(group.value))
  }

  def getFileOwner(path: File): FileOwner = {
    FileOwner(Files.getOwner(path.toPath).getName)
  }

  def removeFile(path: File): Unit = if (path.isDirectory) {
    FileUtils.deleteDirectory(path)
  } else {
    path.delete()
  }

  def changeFileOwner(path: File, owner: FileOwner): Unit = {
    val service = FileSystems.getDefault.getUserPrincipalLookupService

    Files.setOwner(path.toPath, service.lookupPrincipalByName(owner.value))
  }
}
