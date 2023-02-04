package org.pegasus.messenger.server.application.port

interface UpdateUserInputPort {
  fun execute(request: UpdateUserRequest)
}
