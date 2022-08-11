package org.pegasus.messenger.server.model

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "message")
open class Message(
    @field:NotNull
    @field:NotEmpty
    val content: String
) : AbstractEntity<Long>()