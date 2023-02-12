package org.pegasus.messenger.server.messaging

import org.pegasus.messenger.server.kernel.Injectable

@Injectable
class SaveMessageInteractor(private val saveMessageOutputPort: SaveMessageOutputPort) : SaveMessageInputPort {
  override fun execute(request: SaveMessageRequest) = saveMessageOutputPort.save(request)
}
