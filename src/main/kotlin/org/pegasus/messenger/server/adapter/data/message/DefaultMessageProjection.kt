package org.pegasus.messenger.server.adapter.data.message

import org.pegasus.messenger.server.application.port.MessagePort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultMessage", types = [JpaMessage::class])
interface DefaultMessageProjection : MessagePort