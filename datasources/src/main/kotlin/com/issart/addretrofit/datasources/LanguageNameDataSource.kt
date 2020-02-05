package com.issart.addretrofit.datasources

interface LanguageNameDataSource {
    fun getLanguageName(lang: String): String
}