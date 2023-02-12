package org.pegasus.messenger.server.kernel.entity

interface UserPort {
  val id: String
  val username: String
  val email: String
  val firstName: String?
  val lastName: String?
}
