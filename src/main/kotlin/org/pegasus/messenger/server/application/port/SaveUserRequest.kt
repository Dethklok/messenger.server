package org.pegasus.messenger.server.application.port

interface SaveUserRequest {
  val id: String
  val username: String
  val email: String
  val firstName: String?
  val lastName: String?
}