package org.pegasus.messenger.server.userProfile.port

interface UpdateUserInputPort {
  fun execute(request: UpdateUserDto)
}
