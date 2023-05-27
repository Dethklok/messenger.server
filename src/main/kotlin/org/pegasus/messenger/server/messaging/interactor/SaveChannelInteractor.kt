package org.pegasus.messenger.server.messaging.interactor

import org.pegasus.messenger.server.messaging.domain.ChannelPort
import org.pegasus.messenger.server.messaging.port.SaveChannelDto
import org.pegasus.messenger.server.messaging.port.SaveChannelInputPort
import org.pegasus.messenger.server.messaging.port.SaveChannelOutputPort
import org.pegasus.messenger.server.shared.Injectable
import org.pegasus.messenger.server.userProfile.domain.UserPort

@Injectable
class SaveChannelInteractor(
  private val saveChannelOutputPort: SaveChannelOutputPort
) : SaveChannelInputPort {
  override fun execute(request: SaveChannelDto, user: UserPort): ChannelPort {
    return saveChannelOutputPort.save(request.copy(users = request.users.plus(user.id)))
  }
}
