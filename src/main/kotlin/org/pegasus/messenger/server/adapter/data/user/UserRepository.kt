package org.pegasus.messenger.server.adapter.data.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<JpaUser, String> {
  fun findOneById(id: String): DefaultUserProjection?
  fun save(user: JpaUser): DefaultUserProjection
}