package org.pegasus.messenger.server.userProfile.interactor

import org.pegasus.messenger.server.shared.Injectable
import org.pegasus.messenger.server.userProfile.domain.User
import org.pegasus.messenger.server.userProfile.port.FindUserByIdOutputPort
import org.pegasus.messenger.server.userProfile.port.SaveUserOutputPort
import org.pegasus.messenger.server.userProfile.port.UpdateUserInputPort
import org.pegasus.messenger.server.userProfile.port.UpdateUserDto

@Injectable
class UpdateUserInteractor(
  private val findUserByIdOutputPort: FindUserByIdOutputPort,
  private val saveUserOutputPort: SaveUserOutputPort
) : UpdateUserInputPort {
  override fun execute(request: UpdateUserDto) {
    val user = findUserByIdOutputPort.findById(request.userId)

    if (user != null) {
      saveUserOutputPort.save(
        User(
          user.id,
          user.username,
          request.email ?: user.email,
          request.firstName ?: user.firstName,
          request.lastName ?: user.lastName
        )
      )
    }
  }
}
