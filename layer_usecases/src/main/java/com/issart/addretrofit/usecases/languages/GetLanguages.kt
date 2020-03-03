package com.issart.addretrofit.usecases.languages

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.repositories.DictionaryRepository
import com.issart.addretrofit.repositories.SettingsRepository
import com.issart.addretrofit.usecases.LanguagesResultHandler
import javax.inject.Inject

class GetLanguages @Inject constructor(
    private var dictionaryRepository: DictionaryRepository,
    private var settingsRepository: SettingsRepository
) {
    var resultHandler: LanguagesResultHandler = {}

    suspend operator fun invoke() {
        val key = settingsRepository.getApiKey()
        when (val languages = dictionaryRepository.getLanguages(key)) {
            is DataResult.Success -> resultHandler(languages.data)
        }
    }
}
