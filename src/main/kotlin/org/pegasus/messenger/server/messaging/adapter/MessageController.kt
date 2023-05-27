package org.pegasus.messenger.server.messaging.adapter

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.pegasus.messenger.server.messaging.port.SaveMessageDto
import org.pegasus.messenger.server.messaging.domain.MessagePort
import org.pegasus.messenger.server.messaging.port.SaveMessageInputPort
import org.pegasus.messenger.server.shared.adapter.AuthenticationWithUser
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated

@Controller
class MessageController(
  private val saveMessageInputPort: SaveMessageInputPort
) {

  @MessageMapping("/message")
  @SendTo("/topic/message")
  fun save(@Validated saveMessageRequest: SaveMessageRequest, principal: AuthenticationWithUser): MessagePort {
    return saveMessageInputPort.execute(object :
      SaveMessageDto {
      override val content = saveMessageRequest.content
      override val channelId = saveMessageRequest.channelId
      override val userId = principal.user.id
    })
  }

  class SaveMessageRequest(@field:NotEmpty val content: String, @field:NotNull val channelId: Long)
}
