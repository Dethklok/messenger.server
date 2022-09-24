package org.pegasus.messenger.server.application.port

import org.pegasus.messenger.server.domain.entity.Message

interface SaveMessageOutputPort {
  fun save(saveMessageRequest: SaveMessageRequest): Message
}