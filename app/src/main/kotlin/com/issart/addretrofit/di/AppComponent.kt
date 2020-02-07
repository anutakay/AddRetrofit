package com.issart.addretrofit.di

import android.app.Application
import com.issart.addretrofit.App
import com.issart.addretrofit.di.viewmodel.ViewModelModule
import com.issart.addretrofit.presentation.ui.dictionary.DictionaryFragment
import com.issart.addretrofit.presentation.ui.languages.LanguagesFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [DictionaryModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: DictionaryFragment)
    fun inject(fragment: LanguagesFragment)
}