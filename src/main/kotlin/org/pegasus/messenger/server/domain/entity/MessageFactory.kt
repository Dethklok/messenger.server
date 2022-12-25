package org.pegasus.messenger.server.domain.entity

import org.pegasus.messenger.server.kernel.Injectable
import java.time.LocalDateTime

@Injectable
interface MessageFactory {
  fun create(
    id: Long,
    content: String,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
  ): Message
}