package com.issart.addretrofit.datasources

import com.issart.addretrofit.core.DataResult

interface DictionaryDataSource<in INPUT_LANGUAGES, out LANGUAGES, out LOOKUP : Any> {
    suspend fun lookup(key: String, lang: INPUT_LANGUAGES, word: String): DataResult<LOOKUP>
    suspend fun getLanguages(key: String): DataResult<List<LANGUAGES>>
}