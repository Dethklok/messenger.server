package org.pegasus.messenger.server.messaging.port

import org.pegasus.messenger.server.messaging.domain.MessagePort

interface SaveMessageInputPort {
  fun execute(request: SaveMessageDto): MessagePort
}
