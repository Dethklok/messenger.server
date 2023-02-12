package org.pegasus.messenger.server.kernel

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

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
abstract class AbstractJpaEntity<ID : Serializable> : Persistable<ID>, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private var id: ID? = null

  @CreatedDate
  @Column(updatable = false, nullable = false)
  lateinit var createdAt: LocalDateTime

  @LastModifiedDate
  @Column(nullable = false)
  lateinit var updatedAt: LocalDateTime

  @Version
  @JsonIgnore
  private var version: Long? = null

  override fun getId(): ID? = this.id

  @JsonIgnore
  override fun isNew(): Boolean = this.id == null
}
