package com.issart.addretrofit.data.datasources

interface ApiKeyDataSource {
    fun getApiKey(): String
    fun getApiUrl(): String
}