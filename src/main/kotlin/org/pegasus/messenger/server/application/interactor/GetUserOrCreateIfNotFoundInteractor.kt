package org.pegasus.messenger.server.application.interactor

import org.pegasus.messenger.server.application.port.*
import org.pegasus.messenger.server.domain.entity.User
import org.pegasus.messenger.server.kernel.Injectable

@Injectable
class GetUserOrCreateIfNotFoundInteractor(
  private val findUserByIdOutputPort: FindUserByIdOutputPort,
  private val saveUserOutputPort: SaveUserOutputPort
) : GetUserOrCreateIfNotFoundInputPort {
  override fun execute(userRequest: UserPort): User {
    val saveUserOutputPort = findUserByIdOutputPort.findById(userRequest.id)
      ?: saveUserOutputPort.save(userRequest)

    return User(
      saveUserOutputPort.id,
      saveUserOutputPort.username,
      saveUserOutputPort.email,
      saveUserOutputPort.firstName,
      saveUserOutputPort.lastName
    )
  }
}