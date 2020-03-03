package com.issart.addretrofit.data.repositories.dictionary

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.data.datasources.DictionaryDataSource
import com.issart.addretrofit.data.datasources.OpenLanguagesDataSource
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.entities.LookupEntity
import com.issart.addretrofit.repositories.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryDataSource: DictionaryDataSource,
    private val openLanguagesDataSource: OpenLanguagesDataSource<LanguagesEntity>
) : DictionaryRepository {

    override suspend fun getLanguages(key: String): DataResult<List<LanguagesEntity>> =
        dictionaryDataSource.getLanguages(key)

    override suspend fun lookup(
        key: String,
        lang: LanguagesEntity,
        word: String
    ): DataResult<LookupEntity> = dictionaryDataSource.lookup(key, lang, word)

    override fun setOpenLanguages(value: LanguagesEntity) =
        openLanguagesDataSource.setOpenLanguages(value)

    override fun getOpenLanguages(): LanguagesEntity = openLanguagesDataSource.getOpenLanguages()
}

