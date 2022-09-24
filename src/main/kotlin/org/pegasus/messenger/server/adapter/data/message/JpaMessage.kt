package org.pegasus.messenger.server.adapter.data.message

import org.pegasus.messenger.server.adapter.data.common.AbstractJpaEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "message")
class JpaMessage(
    @field:NotEmpty
    val content: String
) : AbstractJpaEntity<Long>()