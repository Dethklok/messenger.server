package org.pegasus.messenger.server.domain.entity

import org.pegasus.messenger.server.application.port.UserPort

class User(
  override val id: String,
  override var username: String,
  override var email: String,
  override var firstName: String?,
  override var lastName: String?,
) : UserPort