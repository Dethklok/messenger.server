package org.pegasus.messenger.server.userProfile.port

import org.pegasus.messenger.server.userProfile.domain.UserPort

interface SaveUserOutputPort {
  fun save(user: UserPort): UserPort
}
