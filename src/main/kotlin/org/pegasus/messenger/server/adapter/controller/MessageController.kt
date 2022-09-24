package org.pegasus.messenger.server.adapter.controller

import org.pegasus.messenger.server.application.interactor.SaveMessageInteractor
import org.pegasus.messenger.server.application.port.SaveMessageRequest
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@Controller
class MessageController(private val saveMessageInteractor: SaveMessageInteractor) {

    @MessageMapping("/message")
    @SendTo("/topic/message")
    fun save(@Validated saveMessageDto: SaveMessageDto) = saveMessageInteractor.saveMessage(SaveMessageRequest(saveMessageDto.content))

    class SaveMessageDto(@field:NotEmpty val content: String)
}