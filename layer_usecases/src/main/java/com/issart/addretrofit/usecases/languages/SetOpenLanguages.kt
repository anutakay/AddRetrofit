package com.issart.addretrofit.usecases.languages

import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.repositories.DictionaryRepository
import javax.inject.Inject

class SetOpenLanguages @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) {
    operator fun invoke(value: LanguagesEntity) = dictionaryRepository.setOpenLanguages(value)
}