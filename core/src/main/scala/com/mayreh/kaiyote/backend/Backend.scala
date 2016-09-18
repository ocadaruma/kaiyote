package com.mayreh.kaiyote.backend

import java.io.File

import com.mayreh.kaiyote.data.{FileGroup, FileOwner, FilePermission}

/**
 * Represents backend to run configurations.
 */
trait Backend extends AutoCloseable {

  def runCommand(cmd: Command): CommandResult

  def isDirectory(path: File): Boolean

  def getFileMode(path: File): FilePermission

  def getFileOwner(path: File): FileOwner

  def getFileGroup(path: File): FileGroup

  def createFileAsDirectory(path: File): Unit

  def changeFileMode(path: File, mode: FilePermission): Unit

  def changeFileOwner(path: File, owner: FileOwner): Unit

  def changeFileGroup(path: File, group: FileGroup): Unit

  def removeFile(path: File): Unit
}
