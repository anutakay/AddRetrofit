package com.issart.addretrofit.data.datasources

interface MessageDataSource {
    fun setMessage(value: String)
    fun getMessage(): String
}
