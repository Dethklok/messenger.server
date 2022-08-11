package org.pegasus.messenger.server.repository

import org.pegasus.messenger.server.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface MessageRepository : JpaRepository<Message, Long>