package com.issart.addretrofit.core.di

import android.app.Application
import com.issart.addretrofit.core.di.viewmodel.ViewModelFactoryModule
import com.issart.addretrofit.datasources.DictionaryApi
import com.issart.addretrofit.di.DictionaryDataModule
import com.issart.addretrofit.repositories.DictionaryRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ViewModelFactoryModule::class,
        AppModule::class,
        DictionaryDataModule::class
    ]
)
@Singleton
interface BaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): BaseComponent
    }

    fun application(): Application
    fun dictionaryRepository(): DictionaryRepository
}