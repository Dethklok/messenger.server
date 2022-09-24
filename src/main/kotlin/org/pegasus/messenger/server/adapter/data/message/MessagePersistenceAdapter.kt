package org.pegasus.messenger.server.adapter.data.message

import org.pegasus.messenger.server.domain.entity.Message
import org.pegasus.messenger.server.application.port.SaveMessageOutputPort
import org.pegasus.messenger.server.application.port.SaveMessageRequest
import org.springframework.stereotype.Component

@Component
class MessagePersistenceAdapter(private val messageRepository: MessageRepository) : SaveMessageOutputPort {
  override fun save(saveMessageRequest: SaveMessageRequest): Message {
    val jpaMessage = messageRepository.save(JpaMessage(saveMessageRequest.content))

    return jpaMessage.id?.let { Message.create(it, jpaMessage.content, jpaMessage.createdAt, jpaMessage.updatedAt) }
      ?: throw IllegalArgumentException("Message's id is required")
  }
}