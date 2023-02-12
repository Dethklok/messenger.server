package org.pegasus.messenger.server.userProfile

import org.pegasus.messenger.server.kernel.entity.UserPort

class User(
  override val id: String,
  override var username: String,
  override var email: String,
  override var firstName: String?,
  override var lastName: String?,
) : UserPort
