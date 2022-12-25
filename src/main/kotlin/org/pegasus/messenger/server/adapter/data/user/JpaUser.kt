package org.pegasus.messenger.server.adapter.data.user

import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import java.io.Serializable
import kotlin.jvm.Transient

@Entity
@Table(name = "user", schema = "public")
class JpaUser(
  @field:[Id Column(unique = true, updatable = false, nullable = false)]
  private var id: String,

  @field:Column(nullable = false)
  val username: String,

  @field:Column(unique = true, nullable = false)
  val email: String,

  val firstName: String?,
  val lastName: String?,
) : Persistable<String>, Serializable {
  @Transient
  private var isNew = false

  override fun isNew() = isNew
  override fun getId() = this.id

  @PostLoad
  @PrePersist
  private fun trackNotNew() {
    isNew = false
  }
}