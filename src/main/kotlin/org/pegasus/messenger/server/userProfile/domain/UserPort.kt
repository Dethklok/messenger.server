package org.pegasus.messenger.server.userProfile.domain

interface UserPort {
  val id: String
  val username: String
  val email: String
  val firstName: String?
  val lastName: String?
}
