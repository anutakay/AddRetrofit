package com.issart.addretrofit.usecases.languages

import com.issart.addretrofit.repositories.DictionaryRepository
import com.issart.addretrofit.usecases.OpenLanguagesHandler
import javax.inject.Inject

class GetOpenLanguages @Inject constructor(private val dictionaryRepository: DictionaryRepository) {
    var resultHandler: OpenLanguagesHandler = {}
    operator fun invoke() = dictionaryRepository.getOpenLanguages().also { resultHandler(it) }
}