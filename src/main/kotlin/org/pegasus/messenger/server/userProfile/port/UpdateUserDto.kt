package org.pegasus.messenger.server.userProfile.port

data class UpdateUserDto(
  val userId: String,
  val email: String?,
  val firstName: String?,
  val lastName: String?
)
