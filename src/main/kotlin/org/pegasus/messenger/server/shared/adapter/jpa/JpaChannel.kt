package org.pegasus.messenger.server.shared.adapter.jpa

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "channel", schema = "public")
class JpaChannel(
  @field:[
  Column(name = "name", nullable = false)
  NotEmpty]
  val name: String,

  @field:[
  ManyToMany(fetch = FetchType.LAZY)
  JoinTable(
    name = "channel_user",
    joinColumns = [JoinColumn(name = "channel_id")],
    inverseJoinColumns = [JoinColumn(name = "user_id")]
  )]
  val users: Set<JpaUser>
) : AbstractJpaEntity<Long>()
