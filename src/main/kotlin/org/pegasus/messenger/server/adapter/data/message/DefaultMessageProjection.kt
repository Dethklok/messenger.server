package org.pegasus.messenger.server.adapter.data.message

import org.springframework.data.rest.core.config.Projection
import java.time.LocalDateTime

@Projection(name = "defaultMessage", types = [ JpaMessage::class ])
interface DefaultMessageProjection {
  val id: Long
  val content: String
  val createdAt: LocalDateTime
  val updatedAt: LocalDateTime
}