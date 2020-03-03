package com.issart.addretrofit.presentation.languages

import androidx.lifecycle.*
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.presentation.ResourcesLanguageNameDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LanguagesViewModel
@Inject constructor(
    private val interactors: LanguagesInteractors,
    private val languageNames: ResourcesLanguageNameDataSource
) : ViewModel() {

    val list = MutableLiveData<List<LanguagesEntity>>()
    private val selectLanguages = MutableLiveData<LanguagesEntity>()
    private val openLanguages = MediatorLiveData<LanguagesEntity>()
    val input = Transformations.map(openLanguages) { languageNames.getLanguageName(it.input) }
    val output = Transformations.map(openLanguages) { languageNames.getLanguageName(it.output) }

    init {
        interactors.getLanguages.resultHandler = { list.postValue(it) }
        interactors.getOpenLanguages.resultHandler = { openLanguages.postValue(it) }
        openLanguages.addSource(selectLanguages) {
            setOpenLanguages(it)
            getOpenLanguages()
        }
    }

    fun initViewModel() {
        getOpenLanguages()
        loadLanguagesList()
    }

    fun selectLanguages(lang: LanguagesEntity) = selectLanguages.postValue(lang)

    private fun loadLanguagesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { interactors.getLanguages() }
        }
    }

    private fun setOpenLanguages(it: LanguagesEntity) = interactors.setOpenLanguages(it)

    private fun getOpenLanguages() = interactors.getOpenLanguages()
}