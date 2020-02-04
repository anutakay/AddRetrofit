package com.issart.addretrofit.presentation.ui.dictionary

import android.app.Application
import androidx.lifecycle.*
import com.issart.addretrofit.AppViewModel
import com.issart.addretrofit.Interactors
import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.domain.LookupResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryViewModel(
    app: Application,
    interactors: Interactors
) : AppViewModel(app, interactors) {

    private val languages: MutableLiveData<Languages> = MutableLiveData()
    val input = Transformations.map(languages, Languages::inputName)
    val output = Transformations.map(languages, Languages::outputName)

    val word: MutableLiveData<String> = MutableLiveData()

    private val rawLookupResult = MediatorLiveData<LookupResult>()
    val lookupResult: LiveData<String> = MediatorLiveData<String>().apply {
        addSource(rawLookupResult) { postValue(toString(it)) }
    }

    init {
        rawLookupResult.addSource(word) { lookup(it, languages.value) }
        rawLookupResult.addSource(languages) { lookup(word.value, it) }

        loadOpenLanguages()
    }

    fun load() = word.postValue("rain")

    fun loadOpenLanguages() = languages.postValue(interactors.getOpenLanguages())

    private fun lookup(word: String?, lang: Languages?) {
        if (word == null || lang == null || lang == Languages.EMPTY) return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.lookup.invoke(
                    lang, word,
                    resultHandler = { rawLookupResult.postValue(it) })
            }
        }
    }

    private fun toString(lookup: LookupResult): String? {
        if (lookup.def.isEmpty()) return "EMPTY RESULT"
        val definition = lookup.def[0]
        return when {
            lookup.def.isEmpty() -> "No definitions"
            lookup.def.size == 1 -> definition.text
            else -> "${definition.text} means ${definition.tr[0].text} and also ${lookup.def.size - 1} definition"
        }
    }
}