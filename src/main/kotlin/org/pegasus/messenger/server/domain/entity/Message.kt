package org.pegasus.messenger.server.domain.entity

import java.time.LocalDateTime

class Message(
  val id: Long,
  val content: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)