package org.pegasus.messenger.server.adapter.controller

import jakarta.validation.constraints.NotEmpty
import org.pegasus.messenger.server.adapter.auth.AuthenticationWithUser
import org.pegasus.messenger.server.application.port.MessagePort
import org.pegasus.messenger.server.application.port.SaveMessageInputPort
import org.pegasus.messenger.server.application.port.SaveMessageRequest
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
  fun save(@Validated saveMessageDto: SaveMessageDto, principal: AuthenticationWithUser): MessagePort {
    return saveMessageInputPort.execute(object : SaveMessageRequest {
      override val content = saveMessageDto.content
      override val userId = principal.user.id
    })
  }

  class SaveMessageDto(@field:NotEmpty val content: String)
}