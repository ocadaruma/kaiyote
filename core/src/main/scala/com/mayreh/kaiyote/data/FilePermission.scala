package com.mayreh.kaiyote.data

case class ParseException(message: String) extends RuntimeException(message)

case class AccessMode(r: Boolean, w: Boolean, x: Boolean) {
  val toInt: Int = (if (r) 4 else 0) +
    (if (w) 2 else 0) +
    (if (x) 1 else 0)
}

object AccessMode {
  val regex = "([0-7])".r
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
 * Currently, special permissions (setuid, setgid, sticky) are not supported.
 */
case class FilePermission(u: AccessMode, g: AccessMode, o: AccessMode) {
  override def toString = s"${u.toInt}${g.toInt}${o.toInt}"
}

object FilePermission {
  val regex = "0?([0-7][0-7][0-7])".r

  /**
   * Parse permission string to FilePermission.
   * @param p permission string. "777", "644", etc...
   * @return parsed FilePermission. If failed to parse, throw ParseExeption.
   */
  def apply(p: String): FilePermission = {
    p match {
      case regex(AccessMode(u), AccessMode(g), AccessMode(o)) => FilePermission(u, g, o)
      case _ => throw ParseException(s"Failed to parse permission. was $p")
    }
  }
}
