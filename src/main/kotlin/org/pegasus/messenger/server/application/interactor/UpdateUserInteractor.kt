package org.pegasus.messenger.server.application.interactor

import org.pegasus.messenger.server.application.port.FindUserByIdOutputPort
import org.pegasus.messenger.server.application.port.SaveUserOutputPort
import org.pegasus.messenger.server.application.port.UpdateUserInputPort
import org.pegasus.messenger.server.application.port.UpdateUserRequest
import org.pegasus.messenger.server.domain.entity.User
import org.pegasus.messenger.server.kernel.Injectable

@Injectable
class UpdateUserInteractor(
  private val findUserByIdOutputPort: FindUserByIdOutputPort,
  private val saveUserOutputPort: SaveUserOutputPort
) : UpdateUserInputPort {
  override fun execute(request: UpdateUserRequest) {
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
