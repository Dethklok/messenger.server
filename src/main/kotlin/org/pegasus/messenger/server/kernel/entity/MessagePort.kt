package org.pegasus.messenger.server.kernel.entity

import org.pegasus.messenger.server.userProfile.ShortUserPort
import java.time.LocalDateTime

interface MessagePort {
  val id: Long
  val content: String
  val createdAt: LocalDateTime
  val updatedAt: LocalDateTime
  val user: ShortUserPort
}
