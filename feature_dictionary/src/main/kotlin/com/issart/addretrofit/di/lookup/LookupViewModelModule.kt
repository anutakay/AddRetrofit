package com.issart.addretrofit.di.lookup

import androidx.lifecycle.ViewModel
import com.issart.addretrofit.core.di.viewmodel.ViewModelKey
import com.issart.addretrofit.presentation.lookup.LookupViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LookupViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LookupViewModel::class)
    abstract fun dictionaryViewModel(viewModel: LookupViewModel): ViewModel
}