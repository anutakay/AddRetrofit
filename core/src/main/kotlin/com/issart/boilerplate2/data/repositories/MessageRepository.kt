package com.issart.boilerplate2.data.repositories

import com.issart.boilerplate2.data.datasources.MessageDataSource
import com.issart.boilerplate2.data.datasources.SuspendedMessageDataSource

class MessageRepository(
    private val messageDataSource: MessageDataSource,
    private val suspendedMessageDataSource: SuspendedMessageDataSource
) {
    fun getMessage() = messageDataSource.getMessage()
    suspend fun getSecondMessage() = suspendedMessageDataSource.getMessage()
}
