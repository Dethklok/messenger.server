package org.pegasus.messenger.server.application.port

interface FindUserByIdOutputPort {
  fun findById(id: String): UserPort?
}