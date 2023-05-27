package org.pegasus.messenger.server.messaging.domain

interface ChannelPort {
  val id: Long
  val name: String
  val users: Set<ShortUserPort>
}
