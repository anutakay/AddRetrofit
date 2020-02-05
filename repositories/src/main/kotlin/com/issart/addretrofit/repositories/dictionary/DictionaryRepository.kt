package com.issart.addretrofit.repositories.dictionary

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.LookupEntity

interface DictionaryRepository {
    suspend fun getLanguages(key: String): DataResult<List<LanguagesEntity>>
    suspend fun lookup(key: String, lang: LanguagesEntity, word: String): DataResult<LookupEntity>
    fun setOpenLanguages(value: LanguagesEntity)
    fun getOpenLanguages(): LanguagesEntity
}