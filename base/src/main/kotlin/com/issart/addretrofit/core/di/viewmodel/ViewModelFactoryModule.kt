package com.issart.addretrofit.core.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.issart.addretrofit.core.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}