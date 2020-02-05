package com.issart.addretrofit.presentation.ui.languages

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.issart.addretrofit.AppViewModel
import com.issart.addretrofit.Interactors
import com.issart.addretrofit.LanguagesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LanguagesViewModel(
    app: Application,
    interactors: Interactors
) : AppViewModel(app, interactors) {

    val list = MutableLiveData<List<LanguagesEntity>>()
    private val selectLanguages = MutableLiveData<LanguagesEntity>()
    val openLanguages = MediatorLiveData<LanguagesEntity>()

    init {
        openLanguages.apply {
            addSource(selectLanguages) {
                interactors.setOpenLanguages(it)
                loadOpenLanguages()
            }
        }
    }

    fun loadLanguagesList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.getLanguages(resultHandler = {
                    list.postValue(it)
                })
            }
        }
    }

    fun loadOpenLanguages() = openLanguages.postValue(interactors.getOpenLanguages())
    fun selectLanguages(lang: LanguagesEntity) = selectLanguages.postValue(lang)
}