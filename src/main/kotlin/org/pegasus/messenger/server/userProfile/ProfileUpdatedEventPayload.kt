package org.pegasus.messenger.server.userProfile

data class ProfileUpdatedEventPayload(
  val id: String,
  val userId: String,
  val time: Long,
  val ip: String,
  val userDetails: Map<String, String>
)
