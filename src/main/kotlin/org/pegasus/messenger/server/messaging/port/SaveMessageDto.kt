package org.pegasus.messenger.server.messaging.port

interface SaveMessageDto {
  val content: String
  val userId: String
  val channelId: Long
}
