package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.shared.adapter.jpa.JpaChannel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

@RepositoryRestResource(
  path = "channels",
  collectionResourceRel = "channels",
  excerptProjection = DefaultChannelProjection::class
)
interface ChannelRepository : JpaRepository<JpaChannel, Long> {
  @RestResource(path = "findByUsersId", rel = "findByUsersId")
  fun findByUsersId(@Param("usersId") usersId: String, page: Pageable): Page<DefaultChannelProjection>
}
