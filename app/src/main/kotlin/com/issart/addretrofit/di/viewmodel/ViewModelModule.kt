package com.issart.addretrofit.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issart.addretrofit.presentation.ui.dictionary.DictionaryViewModel
import com.issart.addretrofit.presentation.ui.languages.LanguagesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DictionaryViewModel::class)
    abstract fun dictionaryViewModel(viewModel: DictionaryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LanguagesViewModel::class)
    abstract fun languagesViewModel(viewModel: LanguagesViewModel): ViewModel
}