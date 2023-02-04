package org.pegasus.messenger.server.adapter.auth

data class ProfileUpdatedEventPayload(
  val id: String,
  val userId: String,
  val time: Long,
  val ip: String,
  val userDetails: Map<String, String>
)
