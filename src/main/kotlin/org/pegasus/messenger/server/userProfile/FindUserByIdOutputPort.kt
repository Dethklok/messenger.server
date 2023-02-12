package org.pegasus.messenger.server.userProfile

import org.pegasus.messenger.server.kernel.entity.UserPort

interface FindUserByIdOutputPort {
  fun findById(id: String): UserPort?
}
