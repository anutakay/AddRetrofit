package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.data.repositories.DictionaryRepositoryImpl
import com.issart.addretrofit.domain.Languages

class SetOpenLanguages(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    operator fun invoke(value: Languages) = dictionaryRepository.setOpenLanguages(value)
}