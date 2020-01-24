package com.issart.boilerplate2.framework.datasource

import com.issart.boilerplate2.data.datasources.SuspendedMessageDataSource
import kotlinx.coroutines.delay

class InMemorySuspendedMessageDataSource : SuspendedMessageDataSource {
    private val message = "Hello again"
    override suspend fun getMessage(): String {
        println("message2 " + Thread.currentThread().name)
        delay(3000)
        return message
    }
}