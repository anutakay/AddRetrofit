package com.issart.addretrofit.framework.device

import com.issart.addretrofit.AppConfig
import com.issart.addretrofit.datasources.ApiKeyDataSource

class ConfigApiKeyDataSource : ApiKeyDataSource {
    override fun getApiKey(): String = AppConfig.yandexSlovaryApiKey
}