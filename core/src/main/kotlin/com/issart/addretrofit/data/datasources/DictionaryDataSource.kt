package com.issart.addretrofit.data.datasources

import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.domain.LookupResult

interface DictionaryDataSource {
    suspend fun lookup(key: String, lang: String, word: String): DataResult<LookupResult>
    suspend fun getLanguages(key: String): DataResult<List<String>>
}