package org.pegasus.messenger.server.adapter.data.user

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.domain.Persistable
import java.io.Serializable
import kotlin.jvm.Transient

@Entity
@Table(name = "`user`", schema = "public")
class JpaUser(
  @field:[Id Column(unique = true, updatable = false, nullable = false)]
  private var id: String,

  @field:Column(name = "username", nullable = false)
  val username: String,

  @field:Column(name = "email", unique = true, nullable = false)
  val email: String,

  @field:Column(name = "firstName")
  val firstName: String?,

  @field:Column(name = "lastName")
  val lastName: String?,
) : Persistable<String>, Serializable {

  @Transient
  private var isNew = false

  @JsonIgnore
  override fun isNew() = isNew
  override fun getId() = this.id

  @PostLoad
  @PrePersist
  private fun trackNotNew() {
    isNew = false
  }
}
