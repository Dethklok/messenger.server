package org.pegasus.messenger.server.messaging.domain

import java.time.LocalDateTime

interface MessagePort {
  val id: Long
  val content: String
  val createdAt: LocalDateTime
  val updatedAt: LocalDateTime
  val user: ShortUserPort
}
