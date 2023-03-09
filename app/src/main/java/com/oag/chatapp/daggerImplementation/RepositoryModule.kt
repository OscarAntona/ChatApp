package com.oag.chatapp.daggerImplementation

import com.oag.chatapp.data.remote.repository.MessagesRepositoryImplementation
import com.oag.chatapp.domain.repository.MessagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMessagesRepository(
        messagesRepository: MessagesRepositoryImplementation
    ): MessagesRepository

}