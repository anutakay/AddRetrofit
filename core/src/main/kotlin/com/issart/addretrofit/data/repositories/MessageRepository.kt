package com.issart.addretrofit.data.repositories

import com.issart.addretrofit.data.datasources.MessageDataSource
import com.issart.addretrofit.data.datasources.SuspendedMessageDataSource

class MessageRepository(
    private val messageDataSource: MessageDataSource,
    private val suspendedMessageDataSource: SuspendedMessageDataSource
) {
    fun getMessage() = messageDataSource.getMessage()
    suspend fun getSecondMessage() = suspendedMessageDataSource.getMessage()
}
