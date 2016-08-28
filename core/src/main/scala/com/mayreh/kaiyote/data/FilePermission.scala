package com.mayreh.kaiyote.data

import java.nio.file.attribute.PosixFilePermission

case class ParseException(message: String) extends RuntimeException(message)

case class AccessMode(r: Boolean, w: Boolean, x: Boolean) {
  val toInt: Int = (if (r) 4 else 0) +
    (if (w) 2 else 0) +
    (if (x) 1 else 0)
}

object AccessMode {

  private[this] val regex = "([0-7])".r

  val empty = AccessMode(false, false, false)

  def apply(p: String): Option[AccessMode] = p match {
    case regex(str) =>
      val num = str.toInt
      Some(AccessMode(
        (num & 4) > 0,
        (num & 2) > 0,
        (num & 1) > 0
      ))
    case _ => None
  }

  def unapply(p: String): Option[AccessMode] = apply(p)
}

/**
 * Represents file permission.
 * FIXME: Currently, special permissions (setuid, setgid, sticky) are not supported.
 */
case class FilePermission(u: AccessMode, g: AccessMode, o: AccessMode) {
  override def toString = s"0${u.toInt}${g.toInt}${o.toInt}"

  val toPosixFilePermission: Set[PosixFilePermission] = {
    import PosixFilePermission._

    val buffer = collection.mutable.Set.empty[PosixFilePermission]

    if (u.r) buffer += OWNER_READ
    if (u.w) buffer += OWNER_WRITE
    if (u.x) buffer += OWNER_EXECUTE

    if (g.r) buffer += GROUP_READ
    if (g.w) buffer += GROUP_WRITE
    if (g.x) buffer += GROUP_EXECUTE

    if (o.r) buffer += OTHERS_READ
    if (o.w) buffer += OTHERS_WRITE
    if (o.x) buffer += OTHERS_EXECUTE

    buffer.toSet
  }
}

object FilePermission {

  private[this] val regex = "0?([0-7])([0-7])([0-7])".r

  val empty = FilePermission(AccessMode.empty, AccessMode.empty, AccessMode.empty)

  /**
   * Parse permission string to FilePermission.
   *
   * @param p permission string. "777", "644", etc...
   * @return parsed FilePermission. If failed to parse, throw ParseExeption.
   */
  def apply(p: String): FilePermission = {
    p match {
      case regex(AccessMode(u), AccessMode(g), AccessMode(o)) => FilePermission(u, g, o)
      case _ => throw ParseException(s"Failed to parse permission. was $p")
    }
  }

  def apply(permissions: Set[PosixFilePermission]): FilePermission =
    permissions.foldLeft(empty) { (acc, p) =>
      import PosixFilePermission._

      p match {
        case OWNER_READ => acc.copy(u = acc.u.copy(r = true))
        case OWNER_WRITE => acc.copy(u = acc.u.copy(w = true))
        case OWNER_EXECUTE => acc.copy(u = acc.u.copy(x = true))

        case GROUP_READ => acc.copy(g = acc.g.copy(r = true))
        case GROUP_WRITE => acc.copy(g = acc.g.copy(w = true))
        case GROUP_EXECUTE => acc.copy(g = acc.g.copy(x = true))

        case OTHERS_READ => acc.copy(o = acc.o.copy(r = true))
        case OTHERS_WRITE => acc.copy(o = acc.o.copy(w = true))
        case OTHERS_EXECUTE => acc.copy(o = acc.o.copy(x = true))

        case _ => acc
      }
    }
}
