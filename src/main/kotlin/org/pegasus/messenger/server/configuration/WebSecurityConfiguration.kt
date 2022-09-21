package org.pegasus.messenger.server.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
class WebSecurityConfiguration(private val opaqueTokenIntrospector: OpaqueTokenIntrospector) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests()
            .antMatchers("/wss-main").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .cors()
            .and()
            .oauth2ResourceServer().opaqueToken()

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
    fun opaqueTokenAuthenticationProvider() = OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector)
}