package org.pegasus.messenger.server.messaging

import org.pegasus.messenger.server.kernel.entity.MessagePort

interface SaveMessageOutputPort {
  fun save(saveMessageRequest: SaveMessageRequest): MessagePort
}
