package com.issart.addretrofit.data.repositories

import com.issart.addretrofit.data.datasources.DictionaryDataSource
import com.issart.addretrofit.data.datasources.OpenLanguagesDataSource
import com.issart.addretrofit.domain.DataResult
import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.domain.LanguagesConverter
import com.issart.addretrofit.domain.LookupResult

class DictionaryRepositoryImpl(
    private val dictionaryDataSource: DictionaryDataSource,
    private val openLanguagesDataSource: OpenLanguagesDataSource,
    private val languagesConverter: LanguagesConverter
) : DictionaryRepository {
    override suspend fun getLanguages(key: String): DataResult<List<Languages>> =
        when (val result = dictionaryDataSource.getLanguages(key)) {
            is DataResult.Success -> DataResult.Success(result.data.map {
                languagesConverter.fromShortcut(it)
            })
            is DataResult.SpecificError -> result
            is DataResult.CommonError -> result
        }

    override suspend fun lookup(
        key: String,
        lang: Languages,
        word: String
    ): DataResult<LookupResult> =
        dictionaryDataSource.lookup(key, languagesConverter.toShortcut(lang), word)

    override fun setOpenLanguages(value: Languages) =
        openLanguagesDataSource.setOpenLanguages(value)

    override fun getOpenLanguages(): Languages = openLanguagesDataSource.getOpenLanguages()

}

