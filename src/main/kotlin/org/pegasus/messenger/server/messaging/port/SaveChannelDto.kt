package org.pegasus.messenger.server.messaging.port

data class SaveChannelDto(
  val name: String,
  val users: Set<String>
)
