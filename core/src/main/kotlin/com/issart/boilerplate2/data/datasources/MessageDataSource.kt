package com.issart.boilerplate2.data.datasources

interface MessageDataSource {
    fun setMessage(value: String)
    fun getMessage(): String
}
