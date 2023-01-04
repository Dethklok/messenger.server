package org.pegasus.messenger.server.application.port

import org.pegasus.messenger.server.domain.entity.User

interface GetUserOrCreateIfNotFoundInputPort {
  fun execute(userRequest: SaveUserRequest): User
}