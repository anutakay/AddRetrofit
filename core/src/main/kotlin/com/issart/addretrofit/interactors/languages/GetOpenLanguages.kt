package com.issart.addretrofit.interactors.languages

import com.issart.addretrofit.data.repositories.DictionaryRepositoryImpl
import com.issart.addretrofit.domain.Languages

class GetOpenLanguages(
    private val dictionaryRepository: DictionaryRepositoryImpl
) {
    operator fun invoke(): Languages = dictionaryRepository.getOpenLanguages()
}