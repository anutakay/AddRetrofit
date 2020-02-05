package com.issart.addretrofit.repositories.settings

import com.issart.addretrofit.datasources.ApiKeyDataSource

class SettingsRepositoryImpl(
    private val apiKeyDataSource: ApiKeyDataSource
) : SettingsRepository {
    override fun getApiKey(): String = apiKeyDataSource.getApiKey()
}

