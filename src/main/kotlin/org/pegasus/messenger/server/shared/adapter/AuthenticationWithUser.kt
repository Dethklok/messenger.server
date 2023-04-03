package org.pegasus.messenger.server.shared.adapter

import org.pegasus.messenger.server.userProfile.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.OAuth2AccessToken
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication

class AuthenticationWithUser(
  principal: OAuth2AuthenticatedPrincipal,
  credentials: OAuth2AccessToken,
  authorities: MutableCollection<out GrantedAuthority>,
  val user: User
) : BearerTokenAuthentication(principal, credentials, authorities)
