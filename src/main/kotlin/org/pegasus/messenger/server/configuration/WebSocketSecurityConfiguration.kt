package org.pegasus.messenger.server.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer

@Configuration
class WebSocketSecurityConfiguration : AbstractSecurityWebSocketMessageBrokerConfigurer() {
    override fun sameOriginDisabled() = true
}