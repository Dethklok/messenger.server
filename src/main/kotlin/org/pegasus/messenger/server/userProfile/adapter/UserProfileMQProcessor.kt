package org.pegasus.messenger.server.userProfile.adapter

import org.apache.kafka.streams.kstream.KStream
import org.pegasus.messenger.server.userProfile.port.UpdateUserDto
import org.pegasus.messenger.server.userProfile.port.UpdateUserInputPort
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class UserProfileMQProcessor(
  private val updateUserInputPort: UpdateUserInputPort
) {

  private val logger = LoggerFactory.getLogger(javaClass)

  @Bean
  fun userProfileProcessor(): Consumer<KStream<String, ProfileUpdatedEventPayload>> = Consumer { kStream ->
    kStream.foreach { _, dto ->
      logger.debug("Got message with dto: {}", dto)

      val request = UpdateUserDto(
        dto.userId,
        dto.userDetails[UpdateProfileEventType.UPDATED_EMAIL.key],
        dto.userDetails[UpdateProfileEventType.UPDATED_FIRST_NAME.key],
        dto.userDetails[UpdateProfileEventType.UPDATED_LAST_NAME.key]
      )

      logger.info("Update user ${dto.userId} with request: $request")

      updateUserInputPort.execute(request)
    }
  }

  enum class UpdateProfileEventType(val key: String) {
    UPDATED_EMAIL("updated_email"),
    UPDATED_FIRST_NAME("updated_first_name"),
    UPDATED_LAST_NAME("updated_last_name")
  }
}
