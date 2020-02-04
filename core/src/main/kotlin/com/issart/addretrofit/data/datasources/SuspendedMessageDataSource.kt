package com.issart.addretrofit.data.datasources

interface SuspendedMessageDataSource {
    suspend fun getMessage(): String
}