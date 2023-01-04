package org.pegasus.messenger.server.adapter.auth

import org.pegasus.messenger.server.application.port.GetUserOrCreateIfNotFoundInputPort
import org.pegasus.messenger.server.application.port.SaveUserRequest
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter
import org.springframework.stereotype.Component

@Component
class AuthenticationConverter(
    private val getUserOrCreateIfNotFoundInputPort: GetUserOrCreateIfNotFoundInputPort
) : OpaqueTokenAuthenticationConverter {
    override fun convert(
        introspectedToken: String,
        authenticatedPrincipal: OAuth2AuthenticatedPrincipal
    ): Authentication {
        val token = OAuth2AccessToken(
            OAuth2AccessToken.TokenType.BEARER,
            introspectedToken,
            null,
            null
        )
        val user = getUserOrCreateIfNotFoundInputPort.execute(
            extractUserDetails(authenticatedPrincipal)
        )
        return AuthenticationWithUser(
            authenticatedPrincipal,
            token,
            authenticatedPrincipal.authorities,
            user
        )
    }

    private fun extractUserDetails(principal: OAuth2AuthenticatedPrincipal): SaveUserRequest {
        try {
            return object : SaveUserRequest {
                override val id = principal.attributes["sub"] as String
                override val username =
                    principal.attributes["username"] as String
                override val email = principal.attributes["email"] as String
                override val firstName =
                    principal.attributes["given_name"] as String?
                override val lastName =
                    principal.attributes["family_name"] as String?
            }
        } catch (e: ClassCastException) {
            throw OAuth2IntrospectionException(e.message)
        }
    }
}