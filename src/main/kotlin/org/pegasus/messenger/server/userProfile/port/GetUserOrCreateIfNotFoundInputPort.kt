package org.pegasus.messenger.server.userProfile.port

import org.pegasus.messenger.server.userProfile.domain.UserPort
import org.pegasus.messenger.server.userProfile.domain.User

interface GetUserOrCreateIfNotFoundInputPort {
  fun execute(userRequest: UserPort): User
}
