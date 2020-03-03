package com.issart.addretrofit.presentation.lookup

import android.app.Application
import androidx.lifecycle.*
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.entities.LookupEntity
import com.issart.addretrofit.presentation.ResourcesLanguageNameDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LookupViewModel
@Inject constructor(
    private val interactors: LookupInteractors,
    private val languageNames: ResourcesLanguageNameDataSource, application: Application
) : AndroidViewModel(application) {

    private val languages: MutableLiveData<LanguagesEntity> = MutableLiveData()
    val input = Transformations.map(languages) { languageNames.getLanguageName(it.input) }
    val output = Transformations.map(languages) { languageNames.getLanguageName(it.output) }

    val word: MutableLiveData<String> = MutableLiveData()

    private val rawLookupResult = MediatorLiveData<LookupEntity>()
    val lookupResult = MediatorLiveData<String>()

    init {
        interactors.getOpenLanguages.resultHandler = { languages.postValue(it) }
        interactors.lookup.resultHandler = { rawLookupResult.postValue(it) }

        rawLookupResult.addSource(word) { lookup(it, languages.value) }
        rawLookupResult.addSource(languages) { lookup(word.value, it) }
        lookupResult.apply { addSource(rawLookupResult) { postValue(toString(it)) } }
    }

    fun initViewModel() {
        loadOpenLanguages()
    }

    fun setWord(value: String) = word.postValue(value)

    private fun lookup(word: String?, lang: LanguagesEntity?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { interactors.lookup(lang, word) }
        }
    }

    private fun loadOpenLanguages() = interactors.getOpenLanguages()

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