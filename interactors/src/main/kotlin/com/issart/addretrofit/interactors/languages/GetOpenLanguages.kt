package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.repositories.dictionary.DictionaryRepositoryImpl

class GetOpenLanguages(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    operator fun invoke(): LanguagesEntity = dictionaryRepository.getOpenLanguages()
}