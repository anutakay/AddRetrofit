package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.repositories.dictionary.DictionaryRepository

class SetOpenLanguages(
    private val dictionaryRepository: DictionaryRepository
) {
    operator fun invoke(value: LanguagesEntity) = dictionaryRepository.setOpenLanguages(value)
}