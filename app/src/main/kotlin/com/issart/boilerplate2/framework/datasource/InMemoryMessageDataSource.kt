package com.issart.boilerplate2.framework.datasource

import com.issart.boilerplate2.data.datasources.MessageDataSource

class InMemoryMessageDataSource : MessageDataSource {

    private var message = "Hello, friends"

    override fun setMessage(value: String) {
        message = value
    }

    override fun getMessage(): String {
        println("message1 " + Thread.currentThread().name)
        return message
    }
}