package org.pegasus.messenger.server.domain.entity

import java.time.LocalDateTime

class CommonMessageFactory : MessageFactory {
  override fun create(id: Long, content: String, createdAt: LocalDateTime, updatedAt: LocalDateTime) =
    Message(id, content, createdAt, updatedAt)
}