package org.pegasus.messenger.server.controller

import org.pegasus.messenger.server.model.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/message")
    fun save(message: Message) {
        println(message)
    }
}