package org.pegasus.messenger.server.messaging.adapter

import jakarta.validation.constraints.NotEmpty
import org.pegasus.messenger.server.messaging.domain.ChannelPort
import org.pegasus.messenger.server.messaging.port.SaveChannelDto
import org.pegasus.messenger.server.messaging.port.SaveChannelInputPort
import org.pegasus.messenger.server.shared.adapter.AuthenticationWithUser
import org.pegasus.messenger.server.shared.adapter.jpa.JpaChannel
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RepositoryRestController("/channels")
class ChannelController(private val saveChannelInputPort: SaveChannelInputPort,
  private val repositoryEntityLinks: RepositoryEntityLinks) {

  @PostMapping
  fun save(
    @RequestBody @Validated saveChannelRequest: SaveChannelRequest,
    principal: AuthenticationWithUser,
  ): ResponseEntity<EntityModel<DefaultChannelProjection>> {
    val saveChannelDto = saveChannelRequest.toDto()
    val channel = saveChannelInputPort.execute(saveChannelDto, principal.user)
    val channelProjection = mapChannelToProjection(channel)

    val resource = EntityModel.of(channelProjection)
    val selfLink = repositoryEntityLinks.linkToItemResource(JpaChannel::class.java, channel.id)

    return ResponseEntity.created(selfLink.toUri()).body(resource.add(selfLink.withSelfRel()))
  }

  private fun mapChannelToProjection(channel: ChannelPort): DefaultChannelProjection {
    return object : DefaultChannelProjection {
      override val id = channel.id
      override val name = channel.name
    }
  }

  class SaveChannelRequest(
    @field:NotEmpty
    val name: String,
    val users: Set<String>?
  ) {
    fun toDto(): SaveChannelDto {
      return SaveChannelDto(
        name = name,
        users = users ?: emptySet()
      )
    }
  }
}
