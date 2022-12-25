package org.pegasus.messenger.server.application.interactor

import org.pegasus.messenger.server.application.port.SaveMessageInputPort
import org.pegasus.messenger.server.application.port.SaveMessageOutputPort
import org.pegasus.messenger.server.application.port.SaveMessageRequest
import org.pegasus.messenger.server.kernel.Injectable

@Injectable
class SaveMessageInteractor(private val saveMessageOutputPort: SaveMessageOutputPort) : SaveMessageInputPort {
  override fun execute(request: SaveMessageRequest) = saveMessageOutputPort.save(request)
}