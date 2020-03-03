package com.issart.addretrofit.di

import com.issart.addretrofit.core.di.BaseComponent
import com.issart.addretrofit.core.di.FeatureScope
import com.issart.addretrofit.di.languages.LanguagesViewModelModule
import com.issart.addretrofit.di.lookup.LookupViewModelModule
import com.issart.addretrofit.presentation.languages.LanguagesFragment
import com.issart.addretrofit.presentation.lookup.LookupFragment
import dagger.Component

@Component(
    dependencies = [BaseComponent::class],
    modules = [
        DictionaryModule::class,
        LanguagesViewModelModule::class,
        LookupViewModelModule::class,
        DictionarySettingsDataModule::class
    ]
)
@FeatureScope
interface DictionaryComponent {
    fun inject(languagesFragment: LanguagesFragment)
    fun inject(lookupFragment: LookupFragment)

    @Component.Builder
    abstract class Builder {
        abstract fun plus(component: BaseComponent): Builder
        abstract fun build(): DictionaryComponent
    }
}