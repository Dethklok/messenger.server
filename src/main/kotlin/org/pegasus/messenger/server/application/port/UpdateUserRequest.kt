package org.pegasus.messenger.server.application.port

data class UpdateUserRequest(
  val userId: String,
  val email: String?,
  val firstName: String?,
  val lastName: String?
)