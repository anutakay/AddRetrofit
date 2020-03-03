package com.issart.addretrofit.di

import android.app.Application
import com.issart.addretrofit.presentation.ResourcesLanguageNameDataSource
import dagger.Module
import dagger.Provides

@Module
object DictionaryModule {

    @JvmStatic
    @Provides
    fun languageNameDataSource(application: Application): ResourcesLanguageNameDataSource =
        ResourcesLanguageNameDataSource(application.applicationContext)
}