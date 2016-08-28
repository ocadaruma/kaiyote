package com.mayreh.kaiyote.backend

import java.io.File

import com.mayreh.kaiyote.data.FilePermission

/**
 * Represents backend to run configurations.
 */
trait Backend extends AutoCloseable {

  def runCommand(cmd: Command): CommandResult

  def isDirectory(path: File): Boolean

  def getFileMode(path: File): FilePermission

  def getOwner(path: File): String

  def getGroup(path: File): String

  def createFileAsDirectory(path: File): Unit

  def changeFileMode(path: File, mode: FilePermission): Unit

  def changeFileOwner(path: File, owner: String)

  def changeFileGroup(path: File, group: String)

  def removeFile(path: File): Unit
}
