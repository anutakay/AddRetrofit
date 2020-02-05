package com.issart.addretrofit.interactors.lookup

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.interactors.LookupResultHandler
import com.issart.addretrofit.repositories.dictionary.DictionaryRepository
import com.issart.addretrofit.repositories.settings.SettingsRepository

class Lookup(
    private var dictionaryRepository: DictionaryRepository,
    private var settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(
        lang: LanguagesEntity,
        word: String,
        resultHandler: LookupResultHandler = {}
    ) {
        val key = settingsRepository.getApiKey()
        when (val lookup = dictionaryRepository.lookup(key, lang, word)) {
            is DataResult.Success -> resultHandler(lookup.data)
        }
    }
}

