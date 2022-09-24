package org.pegasus.messenger.server.domain.entity

import java.time.LocalDateTime

class Message(
  val id: Long,
  val content: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
) {
  companion object {
    fun create(id: Long, content: String, createdAt: LocalDateTime, updatedAt: LocalDateTime) = Message(id, content, createdAt, updatedAt)
  }
}