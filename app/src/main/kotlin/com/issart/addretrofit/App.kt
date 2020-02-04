package com.issart.addretrofit

import android.app.Application
import com.issart.addretrofit.data.repositories.DictionaryRepositoryImpl
import com.issart.addretrofit.data.repositories.SettingsRepositoryImpl
import com.issart.addretrofit.domain.SimpleLanguagesConverter
import com.issart.addretrofit.framework.device.ConfigApiKeyDataSource
import com.issart.addretrofit.data.datasources.InMemoryOpenLanguagesDataSource
import com.issart.addretrofit.framework.network.NetworkDictionaryDataSource
import com.issart.addretrofit.framework.device.ResourcesLanguageNameDataSource
import com.issart.addretrofit.framework.network.DictionaryApi
import com.issart.addretrofit.interactors.languages.GetLanguages
import com.issart.addretrofit.interactors.languages.GetOpenLanguages
import com.issart.addretrofit.interactors.languages.SetOpenLanguages
import com.issart.addretrofit.interactors.lookup.Lookup
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.endpointUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val settingsRepository = SettingsRepositoryImpl(ConfigApiKeyDataSource())

        val dictionaryRepository = DictionaryRepositoryImpl(
            NetworkDictionaryDataSource(
                retrofit.create(DictionaryApi::class.java)
            ),
            InMemoryOpenLanguagesDataSource(),
            SimpleLanguagesConverter(
                ResourcesLanguageNameDataSource(
                    applicationContext
                )
            )
        )

        AppViewModelFactory.inject(
            this, Interactors(
                GetLanguages(dictionaryRepository, settingsRepository),
                SetOpenLanguages(dictionaryRepository),
                GetOpenLanguages(dictionaryRepository),
                Lookup(dictionaryRepository, settingsRepository)
            )
        )
    }
}
