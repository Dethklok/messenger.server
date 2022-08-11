package org.pegasus.messenger.server.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version

/**
 * Class for default auditable model. Extends [Persistable]
 *
 * @param ID the type of the identifier
 * @property id the primary key of model
 * @property createdAt the date when the entity was created (annotated with [CreatedDate])
 * @property updatedAt the date when the entity was updated (annotated with [LastModifiedDate])
 * @property version the version of the entity (annotated with [Version])
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity<ID : Serializable> : Persistable<ID>, Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(updatable = false, nullable = false)
        private val id: ID? = null

        @CreatedDate
        @Column(updatable = false, nullable = false)
        private lateinit var createdAt: LocalDateTime

        @LastModifiedDate
        @Column(nullable = false)
        private lateinit var updatedAt: LocalDateTime

        @Version
        @JsonIgnore
        private var version: Long? = null

        override fun getId(): ID? = this.id

        @JsonIgnore
        override fun isNew(): Boolean = this.id == null
}