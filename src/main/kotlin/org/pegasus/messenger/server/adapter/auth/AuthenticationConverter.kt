package org.pegasus.messenger.server.adapter.auth

import org.pegasus.messenger.server.application.port.GetUserOrCreateIfNotFoundInputPort
import org.pegasus.messenger.server.application.port.UserPort
import org.pegasus.messenger.server.domain.entity.User
import org.slf4j.LoggerFactory
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
  private val logger = LoggerFactory.getLogger(this::class.java)
  override fun convert(
    introspectedToken: String, authenticatedPrincipal: OAuth2AuthenticatedPrincipal
  ): Authentication {
    val token = OAuth2AccessToken(
      OAuth2AccessToken.TokenType.BEARER, introspectedToken, null, null
    )
    val user = getUserOrCreateIfNotFoundInputPort.execute(
      extractUserDetails(authenticatedPrincipal)
    )

    logger.debug("Authentication extended with user: $user")

    return AuthenticationWithUser(
      authenticatedPrincipal, token, authenticatedPrincipal.authorities, user
    )
  }

  private fun extractUserDetails(principal: OAuth2AuthenticatedPrincipal): UserPort {
    try {
      return User(
        principal.attributes["sub"] as String,
        principal.attributes["username"] as String,
        principal.attributes["email"] as String,
        principal.attributes["given_name"] as String?,
        principal.attributes["family_name"] as String?,
      )
    } catch (e: ClassCastException) {
      throw OAuth2IntrospectionException(e.message)
    }
  }
}