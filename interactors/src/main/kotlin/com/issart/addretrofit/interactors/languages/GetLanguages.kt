package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.interactors.LanguagesResultHandler
import com.issart.addretrofit.repositories.dictionary.DictionaryRepositoryImpl
import com.issart.addretrofit.repositories.settings.SettingsRepositoryImpl

class GetLanguages(
    private var dictionaryRepository: DictionaryRepositoryImpl,
    private var settingsRepository: SettingsRepositoryImpl
) {
    suspend operator fun invoke(
        resultHandler: LanguagesResultHandler = {}
    ) {
        val key = settingsRepository.getApiKey()
        when (val languages = dictionaryRepository.getLanguages(key)) {
            is DataResult.Success -> resultHandler(languages.data)
        }
    }
}
