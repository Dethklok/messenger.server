package org.pegasus.messenger.server.adapter.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager

@Configuration
@EnableWebSocketSecurity
class WebSocketSecurityConfiguration {

  @Bean
  fun messageAuthorizationManager(
    messages: MessageMatcherDelegatingAuthorizationManager.Builder
  ): AuthorizationManager<Message<*>> {
    messages.anyMessage().authenticated()
    return messages.build()
  }
}