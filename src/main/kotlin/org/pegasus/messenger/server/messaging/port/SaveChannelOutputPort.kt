package org.pegasus.messenger.server.messaging.port

import org.pegasus.messenger.server.messaging.domain.ChannelPort

interface SaveChannelOutputPort {
  fun save(saveChannelDto: SaveChannelDto): ChannelPort
}
