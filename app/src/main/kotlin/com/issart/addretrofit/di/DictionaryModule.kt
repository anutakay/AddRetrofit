package com.issart.addretrofit.di

import com.issart.addretrofit.AppConfig
import com.issart.addretrofit.Interactors
import com.issart.addretrofit.framework.device.ConfigApiKeyDataSource
import com.issart.addretrofit.framework.network.DictionaryApi
import com.issart.addretrofit.framework.network.NetworkDictionaryDataSource
import com.issart.addretrofit.interactors.languages.GetLanguages
import com.issart.addretrofit.interactors.languages.GetOpenLanguages
import com.issart.addretrofit.interactors.languages.SetOpenLanguages
import com.issart.addretrofit.interactors.lookup.Lookup
import com.issart.addretrofit.repositories.dictionary.DictionaryRepository
import com.issart.addretrofit.repositories.dictionary.DictionaryRepositoryImpl
import com.issart.addretrofit.repositories.settings.SettingsRepository
import com.issart.addretrofit.repositories.settings.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DictionaryModule {

    @Provides
    fun interactors(
        settingsRepository: SettingsRepository,
        dictionaryRepository: DictionaryRepository
    ): Interactors = Interactors(
        GetLanguages(dictionaryRepository, settingsRepository),
        SetOpenLanguages(dictionaryRepository),
        GetOpenLanguages(dictionaryRepository),
        Lookup(dictionaryRepository, settingsRepository)
    )

    @Provides
    fun settingsRepository(): SettingsRepository = SettingsRepositoryImpl(ConfigApiKeyDataSource())

    @Provides
    @Singleton
    fun dictionaryRepository(
        dictionaryDataSource: NetworkDictionaryDataSource
    ): DictionaryRepository = DictionaryRepositoryImpl(dictionaryDataSource)

    @Provides
    fun dictionaryDataSource(dictionaryApi: DictionaryApi): NetworkDictionaryDataSource =
        NetworkDictionaryDataSource(dictionaryApi)

    @Provides
    fun dictionaryApi(): DictionaryApi = Retrofit.Builder()
        .baseUrl(AppConfig.endpointUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DictionaryApi::class.java)
}