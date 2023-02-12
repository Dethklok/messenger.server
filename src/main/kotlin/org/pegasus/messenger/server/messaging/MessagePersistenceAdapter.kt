package org.pegasus.messenger.server.messaging

import org.pegasus.messenger.server.userProfile.UserRepository
import org.pegasus.messenger.server.kernel.entity.MessagePort
import org.pegasus.messenger.server.userProfile.ShortUserPort
import org.springframework.stereotype.Component

@Component
class MessagePersistenceAdapter(
  private val messageRepository: MessageRepository,
  private val userRepository: UserRepository,
) : SaveMessageOutputPort {
  override fun save(saveMessageRequest: SaveMessageRequest): MessagePort {
    val user = userRepository.getReferenceById(saveMessageRequest.userId)
    val message = messageRepository.save(JpaMessage(saveMessageRequest.content, user))
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
