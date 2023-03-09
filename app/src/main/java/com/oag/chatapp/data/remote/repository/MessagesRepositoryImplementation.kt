package com.oag.chatapp.data.remote.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.oag.chatapp.data.remote.Constants.DEFAULT_NETWORK_ERROR
import com.oag.chatapp.util.Resource
import com.oag.chatapp.domain.model.Message
import com.oag.chatapp.domain.repository.MessagesRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessagesRepositoryImplementation @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) :
    MessagesRepository {
    override suspend fun sendMessage(message: Message): Resource<Unit> {
        return try {
            var isSuccessful = false
            firebaseFirestore.collection(message.chatId)
                .document(message.id)
                .set(message, SetOptions.merge())
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()

            if (isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error(DEFAULT_NETWORK_ERROR)
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }


    /* override suspend fun fetchMessagesByChat(chatId: String): Resource<List<Message>> {
         TODO("Not yet implemented")
     }*/
}