package com.oag.chatapp.domain.usecase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.oag.chatapp.domain.model.Message
import com.oag.chatapp.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FetchMessageRealTimeUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend operator fun invoke(chatId: String): Flow<Resource<List<Message>>> = callbackFlow {

        val event = firestore.collection(chatId).orderBy("timestamp", Query.Direction.DESCENDING)

        val subscription = event.addSnapshotListener { snapshot, error ->

            if (error != null) {
                this.trySend(Resource.Error(error.message.toString())).isSuccess
                return@addSnapshotListener
            }

            if (snapshot != null) {

                val messageList = snapshot.toObjects(Message::class.java)

                this.trySend(Resource.Success(messageList)).isSuccess

            }
        }

        awaitClose { subscription.remove() }

    }

}