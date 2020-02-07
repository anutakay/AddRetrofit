package com.issart.addretrofit.presentation.ui.dictionary

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.issart.addretrofit.Interactors
import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.LookupEntity
import com.issart.addretrofit.datasources.LanguageNameDataSource
import com.issart.addretrofit.di.viewmodel.AppViewModel
import com.issart.addretrofit.framework.device.ResourcesLanguageNameDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DictionaryViewModel
@Inject constructor(app: Application, private val interactors: Interactors) : AppViewModel(app) {
    private val languageNames: LanguageNameDataSource =
        ResourcesLanguageNameDataSource(application.applicationContext)

    private val languages: MutableLiveData<LanguagesEntity> = MutableLiveData()
    val input = Transformations.map(languages) { languageNames.getLanguageName(it.input) }
    val output = Transformations.map(languages) { languageNames.getLanguageName(it.output) }

    val word: MutableLiveData<String> = MutableLiveData()

    private val rawLookupResult = MediatorLiveData<LookupEntity>()
    val lookupResult = MediatorLiveData<String>()

    init {
        rawLookupResult.addSource(word) { lookup(it, languages.value) }
        rawLookupResult.addSource(languages) { lookup(word.value, it) }
        lookupResult.apply { addSource(rawLookupResult) { postValue(toString(it)) } }

        loadOpenLanguages()
    }

    fun load() = word.postValue("rain")

    fun loadOpenLanguages() = languages.postValue(interactors.getOpenLanguages())

    private fun lookup(word: String?, lang: LanguagesEntity?) {
        if (word == null || lang == null || lang.isEmpty()) return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.lookup(lang, word) { rawLookupResult.postValue(it) }
            }
        }
    }

    private fun toString(lookup: LookupEntity): String? {
        if (lookup.def.isEmpty()) return "EMPTY RESULT"
        val definition = lookup.def[0]
        return when {
            lookup.def.isEmpty() -> "No definitions"
            lookup.def.size == 1 -> definition.text
            else -> "${definition.text} means ${definition.tr[0].text} and also ${lookup.def.size - 1} definition"
        }
    }
}