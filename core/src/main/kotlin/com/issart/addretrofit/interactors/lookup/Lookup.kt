package com.issart.addretrofit.interactors.lookup

import com.issart.addretrofit.data.repositories.DictionaryRepository
import com.issart.addretrofit.data.repositories.SettingsRepository
import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.interactors.LookupResultHandler

class Lookup(
    private var dictionaryRepository: DictionaryRepository,
    private var settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(
        lang: Languages,
        word: String,
        resultHandler: LookupResultHandler = {}
    ) {
        val key = settingsRepository.getApiKey()
        when (val lookup = dictionaryRepository.lookup(key, lang, word)) {
            is DataResult.Success -> resultHandler(lookup.data)
        }
    }
}

