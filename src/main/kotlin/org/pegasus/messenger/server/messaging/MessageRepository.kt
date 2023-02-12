package org.pegasus.messenger.server.messaging

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
  path = "messages",
  collectionResourceRel = "messages",
  excerptProjection = DefaultMessageProjection::class
)
interface MessageRepository : JpaRepository<JpaMessage, Long>
