package com.mayreh.kaiyote.data

/**
 * Represents backend type.
 */
sealed abstract class BackendType
object BackendType {
  case object Local extends BackendType

  // TODO
  // case object SSH extends BackendType
}

