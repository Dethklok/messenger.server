package org.pegasus.messenger.server.userProfile

interface UpdateUserInputPort {
  fun execute(request: UpdateUserRequest)
}
