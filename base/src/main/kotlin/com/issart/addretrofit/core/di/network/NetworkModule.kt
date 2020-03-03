package com.issart.addretrofit.core.di.network

import com.issart.addretrofit.core.ConfigFileApiKeyDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    fun retrofit(dataSource: ConfigFileApiKeyDataSource): Retrofit =
        Retrofit.Builder()
            .baseUrl(dataSource.getApiUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}