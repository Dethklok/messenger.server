package org.pegasus.messenger.server.messaging.adapter

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import java.time.LocalDateTime

@RepositoryRestResource(
  path = "messages",
  collectionResourceRel = "messages",
  excerptProjection = DefaultMessageProjection::class
)
interface MessageRepository : JpaRepository<JpaMessage, Long> {
  @RestResource(path = "createdAtLessThan", rel = "createdAtLessThan")
  fun findByCreatedAtLessThan(
    @Param("createdAt") createdAt: LocalDateTime,
    page: Pageable
  ): Page<JpaMessage>
}
