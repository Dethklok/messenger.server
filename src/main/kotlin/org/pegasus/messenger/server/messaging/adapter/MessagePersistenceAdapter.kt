package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.messaging.domain.MessagePort
import org.pegasus.messenger.server.messaging.port.SaveMessageDto
import org.pegasus.messenger.server.messaging.port.SaveMessageOutputPort
import org.pegasus.messenger.server.shared.adapter.jpa.JpaMessage
import org.pegasus.messenger.server.userProfile.adapter.UserRepository
import org.springframework.stereotype.Component

@Component
class MessagePersistenceAdapter(
  private val messageRepository: MessageRepository,
  private val userRepository: UserRepository,
  private val channelRepository: ChannelRepository,
  private val userMapper: UserMapper,
) : SaveMessageOutputPort {
  override fun save(saveMessageDto: SaveMessageDto): MessagePort {
    val user = userRepository.getReferenceById(saveMessageDto.userId)
    val channel = channelRepository.getReferenceById(saveMessageDto.channelId)
    val message = messageRepository.save(JpaMessage(saveMessageDto.content, user, channel))

    return object : MessagePort {
      override val id = message.id!!
      override val content = message.content
      override val createdAt = message.createdAt
      override val updatedAt = message.updatedAt
      override val user = userMapper.jpaUserToShortUserPort(message.user)
    }
  }
}
