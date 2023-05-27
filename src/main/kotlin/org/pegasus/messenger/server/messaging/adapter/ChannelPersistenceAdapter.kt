package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.messaging.domain.ChannelPort
import org.pegasus.messenger.server.messaging.port.SaveChannelDto
import org.pegasus.messenger.server.messaging.port.SaveChannelOutputPort
import org.pegasus.messenger.server.shared.adapter.jpa.JpaChannel
import org.pegasus.messenger.server.userProfile.adapter.UserRepository
import org.springframework.stereotype.Component

@Component
class ChannelPersistenceAdapter(
  val userRepository: UserRepository,
  val channelRepository: ChannelRepository,
  val userMapper: UserMapper
) :
  SaveChannelOutputPort {
  override fun save(saveChannelDto: SaveChannelDto): ChannelPort {
    val users = saveChannelDto.users.map { userRepository.getReferenceById(it) }.toSet()
    val channel = channelRepository.save(JpaChannel(saveChannelDto.name, users))

    return object : ChannelPort {
      override val id = channel.id!!
      override val name = channel.name
      override val users = channel.users.map { userMapper.jpaUserToShortUserPort(it) }.toSet()
    }
  }
}
