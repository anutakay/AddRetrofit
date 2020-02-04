package com.issart.addretrofit.data.repositories

import com.issart.addretrofit.data.datasources.ApiKeyDataSource

class SettingsRepositoryImpl(
    private val apiKeyDataSource: ApiKeyDataSource
) : SettingsRepository {
    override fun getApiKey(): String = apiKeyDataSource.getApiKey()
}

