package org.pegasus.messenger.server.application.port

interface GetUserOrCreateIfNotFoundInputPort {
  fun execute(userRequest: SaveUserRequest): UserPort
}