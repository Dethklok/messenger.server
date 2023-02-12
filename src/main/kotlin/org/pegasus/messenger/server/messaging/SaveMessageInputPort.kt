package org.pegasus.messenger.server.messaging

import org.pegasus.messenger.server.kernel.entity.MessagePort

interface SaveMessageInputPort {
  fun execute(request: SaveMessageRequest): MessagePort
}
