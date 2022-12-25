package org.pegasus.messenger.server.adapter.auth

import org.pegasus.messenger.server.application.port.GetUserOrCreateIfNotFoundInputPort
import org.pegasus.messenger.server.application.port.SaveUserRequest
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import org.springframework.stereotype.Component


@Component
class PersistenceUserOpaqueTokenIntrospector(
  private val opaqueTokenIntrospector: OpaqueTokenIntrospector,
  private val getUserOrCreateIfNotFoundInputPort: GetUserOrCreateIfNotFoundInputPort
) : OpaqueTokenIntrospector {

  override fun introspect(token: String?): OAuth2AuthenticatedPrincipal {
    val principal = opaqueTokenIntrospector.introspect(token)
    getUserOrCreateIfNotFoundInputPort.execute(extractUserDetails(principal))
    return DefaultOAuth2AuthenticatedPrincipal(principal.name, principal.attributes, principal.authorities)
  }

  private fun extractUserDetails(principal: OAuth2AuthenticatedPrincipal): SaveUserRequest {
    try {
      return object : SaveUserRequest {
        override val id = principal.attributes["sub"] as String
        override val username = principal.attributes["username"] as String
        override val email = principal.attributes["email"] as String
        override val firstName = principal.attributes["given_name"] as String?
        override val lastName = principal.attributes["family_name"] as String?
      }
    } catch (e: ClassCastException) {
      throw OAuth2IntrospectionException(e.message)
    }
  }
}