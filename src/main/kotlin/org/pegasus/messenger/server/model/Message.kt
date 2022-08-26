package org.pegasus.messenger.server.model

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "message")
class Message(
    @field:NotEmpty
    val content: String
) : AbstractEntity<Long>()