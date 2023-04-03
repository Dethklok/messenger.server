package org.pegasus.messenger.server.adapter.data.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.pegasus.messenger.server.userProfile.domain.User
import org.pegasus.messenger.server.userProfile.adapter.UserPersistenceAdapter
import org.pegasus.messenger.server.userProfile.adapter.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class UserPersistenceAdapterTest {
  @Autowired
  private lateinit var userRepository: UserRepository

  private lateinit var userPersistenceAdapter: UserPersistenceAdapter

  @BeforeEach
  fun setUp() {
    this.userPersistenceAdapter = UserPersistenceAdapter(userRepository)
  }

  @Test
  fun save() {
    val user = User("test_id", "test_username", "test_email@test.com", null, null)
    val savedUser = userPersistenceAdapter.save(user)

    assertEquals(savedUser.id, user.id)
  }
}
