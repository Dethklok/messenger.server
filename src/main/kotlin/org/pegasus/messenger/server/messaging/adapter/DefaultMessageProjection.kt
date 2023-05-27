package org.pegasus.messenger.server.messaging.adapter

import org.pegasus.messenger.server.messaging.domain.MessagePort
import org.pegasus.messenger.server.shared.adapter.jpa.JpaMessage
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultMessage", types = [JpaMessage::class])
interface DefaultMessageProjection : MessagePort
