package com.mayreh.kaiyote.resource

import com.mayreh.kaiyote.backend.Backend
import com.mayreh.kaiyote.logger.Loggable

/**
 * Represents a resource to be configured.
 */
trait Resource[A <: State] extends Loggable {

  /**
   * Display-name.
   */
  def name: String

  /**
   * Desired state.
   */
  def to: A

  /**
   * Current state of this resource.
   */
  def current(backend: Backend): A

  /**
   * Change the state to desired one. (may be side-effecting)
   */
  def run(backend: Backend): Unit
}
