package org.pegasus.messenger.server.application.port

import org.pegasus.messenger.server.domain.entity.Message

interface SaveMessageInputPort {
  fun saveMessage(request: SaveMessageRequest): Message
}