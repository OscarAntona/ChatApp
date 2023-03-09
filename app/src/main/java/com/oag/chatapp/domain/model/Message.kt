package com.oag.chatapp.domain.model
import com.google.firebase.Timestamp
import java.util.Date
import java.util.UUID

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val message: String = "",
    val senderId: String = "",
    val timestamp: Timestamp = Timestamp(Date()),
    val chatId: String = ""
)