package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.repositories.dictionary.DictionaryRepositoryImpl

class SetOpenLanguages(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    operator fun invoke(value: LanguagesEntity) = dictionaryRepository.setOpenLanguages(value)
}