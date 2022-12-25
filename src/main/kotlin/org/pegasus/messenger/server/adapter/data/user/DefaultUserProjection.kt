package org.pegasus.messenger.server.adapter.data.user

import org.pegasus.messenger.server.application.port.UserPort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultUser", types = [JpaUser::class])
interface DefaultUserProjection : UserPort