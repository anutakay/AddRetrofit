package com.issart.addretrofit.data.datasources

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.entities.LookupEntity

interface DictionaryDataSource {
    suspend fun lookup(key: String, lang: LanguagesEntity, word: String): DataResult<LookupEntity>
    suspend fun getLanguages(key: String): DataResult<List<LanguagesEntity>>
}