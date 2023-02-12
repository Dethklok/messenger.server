package org.pegasus.messenger.server.messaging

import jakarta.validation.constraints.NotEmpty
import org.pegasus.messenger.server.security.AuthenticationWithUser
import org.pegasus.messenger.server.kernel.entity.MessagePort
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
