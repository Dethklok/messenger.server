package org.pegasus.messenger.server.application.port

interface SaveMessageRequest {
  val content: String
  val userId: String
}