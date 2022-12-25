package org.pegasus.messenger.server.adapter.configuration

import org.pegasus.messenger.server.adapter.auth.PersistenceUserOpaqueTokenIntrospector
import org.pegasus.messenger.server.application.port.GetUserOrCreateIfNotFoundInputPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
  private val opaqueTokenIntrospector: OpaqueTokenIntrospector,
  private val getUserOrCreateIfNotFoundInputPort: GetUserOrCreateIfNotFoundInputPort
) {

  @Bean
  fun filterChain(
    http: HttpSecurity,
    authenticationConverter: OpaqueTokenAuthenticationConverter
  ): SecurityFilterChain {
    http
      .authorizeHttpRequests {
        it
          .requestMatchers("/wss-main").permitAll()
          .anyRequest().authenticated()
      }
      .csrf().disable()
      .cors()
      .and()
      .oauth2ResourceServer()
      .opaqueToken()
      .authenticationConverter(authenticationConverter)

    return http.build()
  }

  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource {
    val configuration = CorsConfiguration()
    configuration.allowedOrigins = listOf("http://localhost:4200")
    configuration.allowedMethods = listOf("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
    configuration.allowedHeaders = listOf("Content-Type", "Authorization")
    configuration.allowCredentials = true

    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", configuration)

    return source
  }

  @Bean
  fun opaqueTokenAuthenticationProvider() =
    OpaqueTokenAuthenticationProvider(
      PersistenceUserOpaqueTokenIntrospector(opaqueTokenIntrospector, getUserOrCreateIfNotFoundInputPort)
    )

  @Bean
  fun authenticationConverter(opaqueTokenAuthenticationProvider: OpaqueTokenAuthenticationProvider) =
    OpaqueTokenAuthenticationConverter { introspectedToken, _ ->
      val bearerToken = BearerTokenAuthenticationToken(introspectedToken?.replace("Bearer ", ""))
      opaqueTokenAuthenticationProvider.authenticate(bearerToken)
    }
}