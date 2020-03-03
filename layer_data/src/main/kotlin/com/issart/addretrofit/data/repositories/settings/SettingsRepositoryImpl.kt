package com.issart.addretrofit.data.repositories.settings

import com.issart.addretrofit.data.datasources.ApiKeyDataSource
import com.issart.addretrofit.repositories.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val apiKeyDataSource: ApiKeyDataSource
) : SettingsRepository {
    override fun getApiKey(): String = apiKeyDataSource.getApiKey()
}

