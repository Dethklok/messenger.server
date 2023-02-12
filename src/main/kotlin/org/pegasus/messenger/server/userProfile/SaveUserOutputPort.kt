package org.pegasus.messenger.server.userProfile

import org.pegasus.messenger.server.kernel.entity.UserPort

interface SaveUserOutputPort {
  fun save(user: UserPort): UserPort
}
