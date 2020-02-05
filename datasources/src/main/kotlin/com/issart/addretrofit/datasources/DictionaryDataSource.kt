package com.issart.addretrofit.datasources

import com.issart.addretrofit.LookupEntity
import com.issart.addretrofit.core.DataResult

interface DictionaryDataSource {
    suspend fun lookup(key: String, lang: String, word: String): DataResult<LookupEntity>
    suspend fun getLanguages(key: String): DataResult<List<String>>
}