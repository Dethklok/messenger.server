package org.pegasus.messenger.server.adapter.data.user

import org.pegasus.messenger.server.application.port.FindUserByIdOutputPort
import org.pegasus.messenger.server.application.port.SaveUserOutputPort
import org.pegasus.messenger.server.application.port.SaveUserRequest
import org.pegasus.messenger.server.application.port.UserPort
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
  private val userRepository: UserRepository
) : FindUserByIdOutputPort, SaveUserOutputPort {
  override fun findById(id: String): UserPort? {
    return userRepository.findOneById(id)
  }

  override fun save(user: SaveUserRequest): UserPort {
    val jpaUser = JpaUser(user.id, user.username, user.email, user.firstName, user.lastName)
    return userRepository.save(jpaUser)
  }
}