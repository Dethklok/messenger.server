package org.pegasus.messenger.server.application.port

interface UserPort {
  val id: String
  val username: String
  val email: String
  val firstName: String?
  val lastName: String?
}
