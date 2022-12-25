package org.pegasus.messenger.server.application.port

interface SaveMessageOutputPort {
  fun save(saveMessageRequest: SaveMessageRequest): MessagePort
}