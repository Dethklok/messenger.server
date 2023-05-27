package org.pegasus.messenger.server.userProfile.adapter

import org.pegasus.messenger.server.shared.adapter.jpa.JpaUser
import org.pegasus.messenger.server.userProfile.domain.UserPort
import org.springframework.data.rest.core.config.Projection

@Projection(name = "defaultUser", types = [JpaUser::class])
interface DefaultUserProjection : UserPort
