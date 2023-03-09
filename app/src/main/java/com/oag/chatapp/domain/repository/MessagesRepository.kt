package com.oag.chatapp.domain.repository

import com.oag.chatapp.util.Resource
import com.oag.chatapp.domain.model.Message

interface MessagesRepository {
    suspend fun sendMessage(message: Message): Resource<Unit>
    /*suspend fun fetchMessagesByChat(chatId: String): Resource<List<Message>>*/

}