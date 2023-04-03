package org.pegasus.messenger.server.messaging.port

import org.pegasus.messenger.server.messaging.domain.MessagePort

interface SaveMessageOutputPort {
  fun save(saveMessageDto: SaveMessageDto): MessagePort
}
