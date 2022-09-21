package org.pegasus.messenger.server.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration(private val opaqueTokenAuthenticationProvider: OpaqueTokenAuthenticationProvider) : WebSocketMessageBrokerConfigurer {
    @field:Value("\${web.allowed-origin}")
    private lateinit var allowedOrigin: String

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/socket")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/wss-main")
            .setAllowedOrigins(allowedOrigin)
    }

    override fun configureClientInboundChannel(registration: ChannelRegistration) {
        registration.interceptors(object : ChannelInterceptor {
            override fun preSend(message: Message<*>, channel: MessageChannel): Message<*> {
                val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)

                if (StompCommand.CONNECT == accessor?.command) {
                    val token = accessor.getFirstNativeHeader("Authorization")
                        ?: throw AuthenticationCredentialsNotFoundException("Authorization header is not found")

                    val bearerToken = BearerTokenAuthenticationToken(token.replace("Bearer ", ""))
                    val authentication = opaqueTokenAuthenticationProvider.authenticate(bearerToken)
                    accessor.user = authentication
                }

                return message
            }
        })
    }
}