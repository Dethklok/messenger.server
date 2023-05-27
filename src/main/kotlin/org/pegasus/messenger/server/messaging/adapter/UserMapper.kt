package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.messaging.domain.ShortUserPort
import org.pegasus.messenger.server.shared.adapter.jpa.JpaUser
import org.springframework.stereotype.Component

@Component
class UserMapper {
  fun jpaUserToShortUserPort(jpaUser: JpaUser): ShortUserPort {
    return object : ShortUserPort {
      override val id = jpaUser.id
      override val username = jpaUser.username
      override val firstName = jpaUser.firstName
      override val lastName = jpaUser.lastName
    }
  }
}
