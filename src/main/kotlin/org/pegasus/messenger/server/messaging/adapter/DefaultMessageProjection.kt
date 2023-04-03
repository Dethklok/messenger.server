package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.messaging.domain.MessagePort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultMessage", types = [JpaMessage::class])
interface DefaultMessageProjection : MessagePort
