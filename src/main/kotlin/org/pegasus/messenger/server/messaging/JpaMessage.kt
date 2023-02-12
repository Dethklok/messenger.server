package org.pegasus.messenger.server.messaging

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.pegasus.messenger.server.kernel.AbstractJpaEntity
import org.pegasus.messenger.server.userProfile.JpaUser

@Entity
@Table(name = "message", schema = "public")
class JpaMessage(
  @field:[
  Column(name = "content", nullable = false)
  NotEmpty]
  val content: String,

  @field:[
  ManyToOne(optional = false, fetch = FetchType.LAZY)
  JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
  NotNull]
  val user: JpaUser,
) : AbstractJpaEntity<Long>() {
  @Column(name = "user_id", insertable = false, updatable = false)
  val userId = user.id
}
