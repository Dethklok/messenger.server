package org.pegasus.messenger.server.userProfile

import org.pegasus.messenger.server.kernel.entity.UserPort

interface GetUserOrCreateIfNotFoundInputPort {
  fun execute(userRequest: UserPort): User
}
