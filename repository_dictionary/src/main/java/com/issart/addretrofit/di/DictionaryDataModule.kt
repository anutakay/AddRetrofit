package com.issart.addretrofit.di

import com.issart.addretrofit.core.EntityConverter
import com.issart.addretrofit.data.datasources.DictionaryDataSource
import com.issart.addretrofit.data.datasources.InMemoryOpenLanguagesDataSource
import com.issart.addretrofit.data.datasources.OpenLanguagesDataSource
import com.issart.addretrofit.data.repositories.dictionary.DictionaryRepositoryImpl
import com.issart.addretrofit.data.repositories.dictionary.SimpleLanguagesConverter
import com.issart.addretrofit.datasources.DictionaryApi
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.repositories.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [DictionaryDataModule.ModuleBinds::class])
object DictionaryDataModule {

    @JvmStatic
    @Provides
    fun dictionaryApi(retrofit: Retrofit): DictionaryApi =
        retrofit.create(DictionaryApi::class.java)

    @JvmStatic
    @Provides
    fun dictionaryDataSource(
        dictionaryApi: DictionaryApi,
        languagesConverter: EntityConverter<LanguagesEntity, String>
    ): DictionaryDataSource =
        com.issart.addretrofit.datasources.NetworkDictionaryDataSource(
            dictionaryApi,
            languagesConverter
        )

    @JvmStatic
    @Provides
    fun openLanguagesDataSource(): OpenLanguagesDataSource<LanguagesEntity> =
        InMemoryOpenLanguagesDataSource(LanguagesEntity())

    @Provides
    fun languagesConverter(): EntityConverter<LanguagesEntity, String> =
        SimpleLanguagesConverter()

    @Module
    abstract class ModuleBinds {
        @Binds
        abstract fun dictionaryRepository(impl: DictionaryRepositoryImpl): DictionaryRepository
    }
}