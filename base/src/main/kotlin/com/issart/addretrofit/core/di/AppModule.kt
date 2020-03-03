package com.issart.addretrofit.core.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.issart.addretrofit.core.ConfigHelper
import com.issart.addretrofit.core.R
import com.issart.addretrofit.core.di.network.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideRequestOptions(): RequestOptions =
        RequestOptions()
            .placeholder(R.color.secondaryLightColor)
            .error(R.color.error)

    @JvmStatic
    @Provides
    @Singleton
    fun provideGlideInstance(
        application: Application,
        options: RequestOptions
    ): RequestManager =
        Glide.with(application)
            .setDefaultRequestOptions(options)

    @JvmStatic
    @Provides
    fun provideConfigHelper(): ConfigHelper = ConfigHelper()
}