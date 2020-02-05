package com.issart.addretrofit.datasources

interface OpenLanguagesDataSource<LANGUAGES> {
    fun setOpenLanguages(value: LANGUAGES)
    fun getOpenLanguages(): LANGUAGES
}