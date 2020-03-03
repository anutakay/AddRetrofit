package com.issart.addretrofit.di.languages

import androidx.lifecycle.ViewModel
import com.issart.addretrofit.core.di.viewmodel.ViewModelKey
import com.issart.addretrofit.presentation.languages.LanguagesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LanguagesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LanguagesViewModel::class)
    abstract fun languagesViewModel(viewModel: LanguagesViewModel): ViewModel
}