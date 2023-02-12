package org.pegasus.messenger.server.userProfile

import org.pegasus.messenger.server.kernel.entity.UserPort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultUser", types = [JpaUser::class])
interface DefaultUserProjection : UserPort
