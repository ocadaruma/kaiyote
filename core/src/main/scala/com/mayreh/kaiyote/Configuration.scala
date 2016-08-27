package com.mayreh.kaiyote

import com.mayreh.kaiyote.resource.Resource

/**
 * Represents configuration to be applied.
 */
case class Configuration(resources: Vector[Resource] = Vector.empty) {
  def ++(resource: Resource): Configuration = Configuration(resources :+ resource)
}
