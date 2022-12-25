package org.pegasus.messenger.server.adapter.data.message

import org.pegasus.messenger.server.adapter.data.user.UserRepository
import org.pegasus.messenger.server.application.port.MessagePort
import org.pegasus.messenger.server.application.port.SaveMessageOutputPort
import org.pegasus.messenger.server.application.port.SaveMessageRequest
import org.pegasus.messenger.server.application.port.ShortUserPort
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