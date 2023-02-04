package org.pegasus.messenger.server.adapter.data.user

import org.pegasus.messenger.server.application.port.FindUserByIdOutputPort
import org.pegasus.messenger.server.application.port.SaveUserOutputPort
import org.pegasus.messenger.server.application.port.UserPort
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
  private val userRepository: UserRepository
) : FindUserByIdOutputPort, SaveUserOutputPort {
  override fun findById(id: String): UserPort? {
    return userRepository.findOneById(id)
  }

  override fun save(user: UserPort): UserPort {
    val jpaUser = JpaUser(user.id, user.username, user.email, user.firstName, user.lastName)
    val savedUser = userRepository.save(jpaUser)

    return object : UserPort {
      override val id = savedUser.id
      override val username = savedUser.username
      override val email = savedUser.email
      override val firstName = savedUser.firstName
      override val lastName = savedUser.lastName
    }
  }
}
