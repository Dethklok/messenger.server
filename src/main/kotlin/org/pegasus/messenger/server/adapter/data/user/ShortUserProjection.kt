package org.pegasus.messenger.server.adapter.data.user

import org.pegasus.messenger.server.application.port.ShortUserPort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "ShortUser", types = [JpaUser::class])
interface ShortUserProjection : ShortUserPort