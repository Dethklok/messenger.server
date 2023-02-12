package org.pegasus.messenger.server.messaging

interface SaveMessageRequest {
  val content: String
  val userId: String
}
