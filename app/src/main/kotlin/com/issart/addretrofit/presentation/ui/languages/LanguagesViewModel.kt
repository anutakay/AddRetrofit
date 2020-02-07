package com.issart.addretrofit.presentation.ui.languages

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.issart.addretrofit.Interactors
import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.datasources.LanguageNameDataSource
import com.issart.addretrofit.di.viewmodel.AppViewModel
import com.issart.addretrofit.framework.device.ResourcesLanguageNameDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LanguagesViewModel
@Inject constructor(app: Application, private val interactors: Interactors) : AppViewModel(app) {

    private val languageNames: LanguageNameDataSource =
        ResourcesLanguageNameDataSource(application.applicationContext)

    val list = MutableLiveData<List<LanguagesEntity>>()
    private val selectLanguages = MutableLiveData<LanguagesEntity>()
    private val openLanguages = MediatorLiveData<LanguagesEntity>()
    val input = Transformations.map(openLanguages) { languageNames.getLanguageName(it.input) }
    val output = Transformations.map(openLanguages) { languageNames.getLanguageName(it.output) }

    init {
        openLanguages.addSource(selectLanguages) {
            interactors.setOpenLanguages(it)
            loadOpenLanguages()
        }
    }

    fun loadLanguagesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.getLanguages { list.postValue(it) }
            }
        }
    }

    fun loadOpenLanguages() = openLanguages.postValue(interactors.getOpenLanguages())
    fun selectLanguages(lang: LanguagesEntity) = selectLanguages.postValue(lang)
}