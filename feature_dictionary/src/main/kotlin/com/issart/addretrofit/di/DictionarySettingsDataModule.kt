package com.issart.addretrofit.di

import com.issart.addretrofit.core.ConfigFileApiKeyDataSource
import com.issart.addretrofit.data.datasources.ApiKeyDataSource
import com.issart.addretrofit.repositories.SettingsRepository
import com.issart.addretrofit.data.repositories.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DictionarySettingsDataModule.BindsModule::class])
object DictionarySettingsDataModule {
    @Module
    abstract class BindsModule {
        @Binds
        abstract fun apiKeyDataSource(apiKeyDataSource: ConfigFileApiKeyDataSource): ApiKeyDataSource

        @Binds
        abstract fun settingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
    }
}