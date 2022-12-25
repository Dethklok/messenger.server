package org.pegasus.messenger.server.domain.entity

import org.pegasus.messenger.server.application.port.UserPort

class User(
  override val id: String,
  override val username: String,
  override val email: String,
  override val firstName: String?,
  override val lastName: String?,
) : UserPort