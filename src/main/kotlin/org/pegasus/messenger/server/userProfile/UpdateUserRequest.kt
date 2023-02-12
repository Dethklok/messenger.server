package org.pegasus.messenger.server.userProfile

data class UpdateUserRequest(
  val userId: String,
  val email: String?,
  val firstName: String?,
  val lastName: String?
)
