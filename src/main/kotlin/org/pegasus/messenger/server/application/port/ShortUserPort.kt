package org.pegasus.messenger.server.application.port

interface ShortUserPort {
  val id: String
  val username: String
  val firstName: String?
  val lastName: String?
}