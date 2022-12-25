package org.pegasus.messenger.server.application.port

import java.time.LocalDateTime

interface MessagePort {
  val id: Long
  val content: String
  val createdAt: LocalDateTime
  val updatedAt: LocalDateTime
  val user: ShortUserPort
}