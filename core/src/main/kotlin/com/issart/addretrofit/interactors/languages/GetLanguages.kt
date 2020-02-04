package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.data.repositories.DictionaryRepositoryImpl
import com.issart.addretrofit.data.repositories.SettingsRepositoryImpl
import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.interactors.LanguagesResultHandler

class GetLanguages(
    private var dictionaryRepository: DictionaryRepositoryImpl,
    private var settingsRepository: SettingsRepositoryImpl
) {
    suspend operator fun invoke(
        resultHandler: LanguagesResultHandler = {}
    ) {
        when (val languages = dictionaryRepository.getLanguages(settingsRepository.getApiKey())) {
            is DataResult.Success -> resultHandler(languages.data)
        }
    }
}
