package com.issart.addretrofit.repositories.dictionary

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.LookupEntity
import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.core.EntityConverter
import com.issart.addretrofit.datasources.DictionaryDataSource
import com.issart.addretrofit.datasources.OpenLanguagesDataSource

class DictionaryRepositoryImpl(
    private val dictionaryDataSource: DictionaryDataSource,
    private val openLanguagesDataSource: OpenLanguagesDataSource,
    private val languagesConverter: EntityConverter<LanguagesEntity, String>
) : DictionaryRepository {
    override suspend fun getLanguages(key: String): DataResult<List<LanguagesEntity>> =
        when (val result = dictionaryDataSource.getLanguages(key)) {
            is DataResult.Success -> DataResult.Success(result.data.map {
                languagesConverter.toEntity(it)
            })
            is DataResult.SpecificError<*> -> result
            is DataResult.CommonError -> result
        }

    override suspend fun lookup(
        key: String,
        lang: LanguagesEntity,
        word: String
    ): DataResult<LookupEntity> =
        dictionaryDataSource.lookup(key, languagesConverter.fromEntity(lang), word)

    override fun setOpenLanguages(value: LanguagesEntity) =
        openLanguagesDataSource.setOpenLanguages(value)

    override fun getOpenLanguages(): LanguagesEntity = openLanguagesDataSource.getOpenLanguages()

}

