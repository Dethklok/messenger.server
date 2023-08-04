package org.pegasus.messenger.server.shared.adapter.jpa

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@Entity(name = "message")
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

  @field:[
  ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
  JoinColumn(name = "channel_id", nullable = false, referencedColumnName = "id", updatable = false)
  NotNull]
  val channel: JpaChannel
) : AbstractJpaEntity<Long>() {
  @Column(name = "user_id", insertable = false, updatable = false)
  val userId = user.id
}
