package com.issart.addretrofit.core

import android.app.Application
import com.issart.addretrofit.core.ConfigHelper
import com.issart.addretrofit.data.datasources.ApiKeyDataSource
import javax.inject.Inject

class ConfigFileApiKeyDataSource @Inject constructor(
    val app: Application,
    private val configHelper: ConfigHelper
) : ApiKeyDataSource {

    private val key by lazy {
        configHelper.getConfigValue(app.applicationContext, "dictionary_api_key")
    }
    private val url by lazy {
        configHelper.getConfigValue(app.applicationContext, "dictionary_api_url")
    }

    override fun getApiKey(): String = key
    override fun getApiUrl(): String = url
}