package com.issart.addretrofit.framework.network

import com.issart.addretrofit.LookupEntity
import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.datasources.DictionaryDataSource

class NetworkDictionaryDataSource(
    private val dictionaryApi: DictionaryApi
) : NetworkDataSource(), DictionaryDataSource {

    override suspend fun getLanguages(key: String): DataResult<List<String>> =
        safeApiCall(
            {
                dictionaryApi.getLangs(key)
                //throw IOException("My exception")
            },
            "Error during fetching list of languages"
        )

    override suspend fun lookup(
        key: String,
        lang: String,
        word: String
    ): DataResult<LookupEntity> = safeApiCall({ dictionaryApi.lookup(key, lang, word) })
}