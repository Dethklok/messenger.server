package org.pegasus.messenger.server.messaging.port

import org.pegasus.messenger.server.messaging.domain.ChannelPort
import org.pegasus.messenger.server.userProfile.domain.UserPort

interface SaveChannelInputPort {
  fun execute(request: SaveChannelDto, user: UserPort): ChannelPort
}
