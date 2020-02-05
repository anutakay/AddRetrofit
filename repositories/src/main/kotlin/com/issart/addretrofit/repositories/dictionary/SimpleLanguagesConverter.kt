package com.issart.addretrofit.repositories.dictionary

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.core.EntityConverter
import com.issart.addretrofit.datasources.LanguageNameDataSource

class SimpleLanguagesConverter(
    private val languageNameDataSource: LanguageNameDataSource
) : EntityConverter<LanguagesEntity, String> {

    override fun toEntity(value: String): LanguagesEntity {
        val pair = value.split(DELIMITER)
        return when (pair.size) {
            2 -> LanguagesEntity(
                pair[0], languageNameDataSource.getLanguageName(pair[0]),
                pair[1], languageNameDataSource.getLanguageName(pair[1])
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun fromEntity(value: LanguagesEntity): String =
        "${value.input}$DELIMITER${value.output}"

    companion object {
        private const val DELIMITER = "-"
    }
}