package com.issart.addretrofit.data.repositories

import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.domain.LookupResult

interface DictionaryRepository {
    suspend fun getLanguages(key: String): DataResult<List<Languages>>
    suspend fun lookup(key: String, lang: Languages, word: String): DataResult<LookupResult>
    fun setOpenLanguages(value: Languages)
    fun getOpenLanguages(): Languages
}