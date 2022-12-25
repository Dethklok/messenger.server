package org.pegasus.messenger.server.domain.entity

import org.pegasus.messenger.server.application.port.UserPort
import org.pegasus.messenger.server.kernel.Injectable

@Injectable
class CommonUserFactory : UserFactory {
  override fun create(userPort: UserPort) =
    User(
      userPort.id,
      userPort.username,
      userPort.email,
      userPort.firstName,
      userPort.lastName
    )
}