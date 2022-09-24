package org.pegasus.messenger.server.adapter.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
class RestConfiguration : RepositoryRestConfigurer {
    @field:Value("\${web.allowed-origin}")
    private lateinit var allowedOrigin: String
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?, cors: CorsRegistry?) {
        config?.setBasePath("api")
        cors?.addMapping("/*")
            ?.allowedOrigins(allowedOrigin)
            ?.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
    }

    override fun configureValidatingRepositoryEventListener(validatingListener: ValidatingRepositoryEventListener?) {
        val validator = validator()
        validatingListener?.addValidator("beforeCreate", validator)
        validatingListener?.addValidator("beforeSave", validator)
    }

    @Bean
    fun validator(): Validator = LocalValidatorFactoryBean()
}