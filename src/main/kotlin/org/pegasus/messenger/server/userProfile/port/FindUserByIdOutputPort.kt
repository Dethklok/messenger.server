package org.pegasus.messenger.server.userProfile.port

import org.pegasus.messenger.server.userProfile.domain.UserPort

interface FindUserByIdOutputPort {
  fun findById(id: String): UserPort?
}
