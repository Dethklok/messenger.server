package org.pegasus.messenger.server.messaging.domain

interface ShortUserPort {
  val id: String
  val username: String
  val firstName: String?
  val lastName: String?
}
