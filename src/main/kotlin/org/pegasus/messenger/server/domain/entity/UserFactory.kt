package org.pegasus.messenger.server.domain.entity

import org.pegasus.messenger.server.application.port.UserPort

interface UserFactory {
  fun create(userPort: UserPort): User
}