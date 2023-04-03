package org.pegasus.messenger.server.messaging.interactor

import org.pegasus.messenger.server.shared.Injectable
import org.pegasus.messenger.server.messaging.port.SaveMessageInputPort
import org.pegasus.messenger.server.messaging.port.SaveMessageOutputPort
import org.pegasus.messenger.server.messaging.port.SaveMessageDto

@Injectable
class SaveMessageInteractor(private val saveMessageOutputPort: SaveMessageOutputPort) : SaveMessageInputPort {
  override fun execute(request: SaveMessageDto) = saveMessageOutputPort.save(request)
}
