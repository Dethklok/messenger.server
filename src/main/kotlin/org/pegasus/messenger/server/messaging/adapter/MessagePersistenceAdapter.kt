package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.messaging.port.SaveMessageDto
import org.pegasus.messenger.server.userProfile.adapter.UserRepository
import org.pegasus.messenger.server.messaging.domain.MessagePort
import org.pegasus.messenger.server.messaging.domain.ShortUserPort
import org.pegasus.messenger.server.messaging.port.SaveMessageOutputPort
import org.springframework.stereotype.Component

@Component
class MessagePersistenceAdapter(
  private val messageRepository: MessageRepository,
  private val userRepository: UserRepository,
) : SaveMessageOutputPort {
  override fun save(saveMessageDto: SaveMessageDto): MessagePort {
    val user = userRepository.getReferenceById(saveMessageDto.userId)
    val message = messageRepository.save(JpaMessage(saveMessageDto.content, user))
    val id = message.id as Long
    return object : MessagePort {
      override val id = id
      override val content = message.content
      override val createdAt = message.createdAt
      override val updatedAt = message.updatedAt
      override val user = object : ShortUserPort {
        override val id = message.user.id
        override val username = message.user.username
        override val firstName = message.user.firstName
        override val lastName = message.user.lastName
      }
    }
  }
}
