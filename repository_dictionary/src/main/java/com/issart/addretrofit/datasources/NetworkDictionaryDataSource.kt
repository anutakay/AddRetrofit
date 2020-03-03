package com.issart.addretrofit.datasources

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.core.EntityConverter
import com.issart.addretrofit.data.datasources.DictionaryDataSource
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.entities.LookupEntity

class NetworkDictionaryDataSource(
    private val dictionaryApi: DictionaryApi,
    private val languagesConverter: EntityConverter<LanguagesEntity, String>
) : com.issart.addretrofit.datasources.NetworkDataSource(), DictionaryDataSource {

    override suspend fun getLanguages(key: String): DataResult<List<LanguagesEntity>> {
        val result = safeApiCall(
            { dictionaryApi.getLangs(key) },
            "Error during fetching list of languages"
        )
        return when (result) {
            is DataResult.Success -> DataResult.Success(
                result.data.map { languagesConverter.toEntity(it) }
            )
            is DataResult.SpecificError<*> -> result
            is DataResult.CommonError -> result
        }
    }

    override suspend fun lookup(
        key: String,
        lang: LanguagesEntity,
        word: String
    ): DataResult<LookupEntity> = safeApiCall({
        dictionaryApi.lookup(key, languagesConverter.fromEntity(lang), word)
    })
}