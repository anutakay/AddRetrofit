package com.issart.addretrofit.usecases.lookup

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.repositories.DictionaryRepository
import com.issart.addretrofit.repositories.SettingsRepository
import com.issart.addretrofit.usecases.LookupResultHandler
import javax.inject.Inject

class Lookup @Inject constructor(
    private var dictionaryRepository: DictionaryRepository,
    private var settingsRepository: SettingsRepository
) {
    var resultHandler: LookupResultHandler = {}
    suspend operator fun invoke(
        lang: LanguagesEntity?,
        word: String?
    ) {
        if (word == null || lang == null || lang.isEmpty()) return
        val key = settingsRepository.getApiKey()
        when (val lookup = dictionaryRepository.lookup(key, lang, word)) {
            is DataResult.Success -> resultHandler(lookup.data)
        }
    }
}

