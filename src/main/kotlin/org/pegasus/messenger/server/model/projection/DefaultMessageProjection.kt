package org.pegasus.messenger.server.model.projection

import org.pegasus.messenger.server.model.Message
import org.springframework.data.rest.core.config.Projection
import java.time.LocalDateTime

@Projection(name = "defaultMessage", types = [ Message::class ])
interface DefaultMessageProjection {
    val id: Long?
    val createdAt: LocalDateTime?
    val updatedAt: LocalDateTime?
    val content: String?
}