package org.pegasus.messenger.server.configuration

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
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?, cors: CorsRegistry?) {
        config?.setBasePath("api")
        cors?.addMapping("/*")
            ?.allowedOrigins("127.0.0.1:4200")
            ?.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
    }

    override fun configureValidatingRepositoryEventListener(validatingListener: ValidatingRepositoryEventListener?) {
        val validator = validator()
        validatingListener?.addValidator("beforeCreate", validator)
        validatingListener?.addValidator("beforeSave", validator)
    }

    @Bean
    fun validator(): Validator = LocalValidatorFactoryBean()
}