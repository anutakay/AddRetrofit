package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.repositories.dictionary.DictionaryRepository

class GetOpenLanguages(
    private val dictionaryRepository: DictionaryRepository
) {
    operator fun invoke(): LanguagesEntity = dictionaryRepository.getOpenLanguages()
}