package com.issart.boilerplate2.data.datasources

interface SuspendedMessageDataSource {
    suspend fun getMessage(): String
}