package org.pegasus.messenger.server.repository

import org.pegasus.messenger.server.model.Message
import org.pegasus.messenger.server.model.projection.DefaultMessageProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(
    path = "messages",
    collectionResourceRel = "messages",
    excerptProjection = DefaultMessageProjection::class)
interface MessageRepository : JpaRepository<Message, Long>