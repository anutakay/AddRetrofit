package com.issart.addretrofit.data.datasources

interface OpenLanguagesDataSource<LANGUAGES> {
    fun setOpenLanguages(value: LANGUAGES)
    fun getOpenLanguages(): LANGUAGES
}