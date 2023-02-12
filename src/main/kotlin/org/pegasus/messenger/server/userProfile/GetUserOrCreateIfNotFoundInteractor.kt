package org.pegasus.messenger.server.userProfile

import org.pegasus.messenger.server.kernel.Injectable
import org.pegasus.messenger.server.kernel.entity.UserPort

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
