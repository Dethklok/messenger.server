package org.pegasus.messenger.server.userProfile.interactor

import org.pegasus.messenger.server.shared.Injectable
import org.pegasus.messenger.server.userProfile.domain.UserPort
import org.pegasus.messenger.server.userProfile.domain.User
import org.pegasus.messenger.server.userProfile.port.FindUserByIdOutputPort
import org.pegasus.messenger.server.userProfile.port.GetUserOrCreateIfNotFoundInputPort
import org.pegasus.messenger.server.userProfile.port.SaveUserOutputPort

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
