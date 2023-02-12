package org.pegasus.messenger.server.messaging

import org.pegasus.messenger.server.kernel.entity.MessagePort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultMessage", types = [JpaMessage::class])
interface DefaultMessageProjection : MessagePort
