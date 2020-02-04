package com.issart.addretrofit.framework.network

import com.issart.addretrofit.data.datasources.DictionaryDataSource
import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.domain.LookupResult
import java.io.IOException

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
    ): DataResult<LookupResult> = safeApiCall({ dictionaryApi.lookup(key, lang, word) })
}