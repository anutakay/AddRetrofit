package com.issart.addretrofit.domain

class SimpleLanguagesConverter(
    private val languageNameDataSource: LanguageNameDataSource
) : LanguagesConverter {

    override fun fromShortcut(value: String): Languages {
        val pair = value.split(delimiter)
        return when (pair.size) {
            2 -> Languages(
                pair[0], languageNameDataSource.getLanguageName(pair[0]),
                pair[1], languageNameDataSource.getLanguageName(pair[1])
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun toShortcut(value: Languages): String = "${value.input}$delimiter${value.output}"

    interface LanguageNameDataSource {
        fun getLanguageName(lang: String): String
    }

    companion object {
        const val delimiter = "-"
    }
}