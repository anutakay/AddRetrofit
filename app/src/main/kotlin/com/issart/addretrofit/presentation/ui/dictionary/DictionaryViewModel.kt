package com.issart.addretrofit.presentation.ui.dictionary

import android.app.Application
import androidx.lifecycle.*
import com.issart.addretrofit.AppViewModel
import com.issart.addretrofit.Interactors
import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.LookupEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryViewModel(
    app: Application,
    interactors: Interactors
) : AppViewModel(app, interactors) {

    private val languages: MutableLiveData<LanguagesEntity> = MutableLiveData()
    val input = Transformations.map(languages, LanguagesEntity::input)
    val output = Transformations.map(languages, LanguagesEntity::output)

    val word: MutableLiveData<String> = MutableLiveData()

    private val rawLookupResult = MediatorLiveData<LookupEntity>()
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

    private fun lookup(word: String?, lang: LanguagesEntity?) {
        if (word == null || lang == null || lang.isEmpty()) return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactors.lookup.invoke(
                    lang, word,
                    resultHandler = { rawLookupResult.postValue(it) })
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