package org.pegasus.messenger.server.userProfile.domain

class User(
  override val id: String,
  override var username: String,
  override var email: String,
  override var firstName: String?,
  override var lastName: String?,
) : UserPort
