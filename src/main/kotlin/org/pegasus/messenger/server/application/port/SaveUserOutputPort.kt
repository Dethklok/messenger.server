package org.pegasus.messenger.server.application.port

interface SaveUserOutputPort {
  fun save(user: UserPort): UserPort
}