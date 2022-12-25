package org.pegasus.messenger.server.application.port

interface SaveMessageInputPort {
  fun execute(request: SaveMessageRequest): MessagePort
}