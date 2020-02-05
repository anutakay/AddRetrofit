package com.issart.addretrofit.datasources

import com.issart.addretrofit.LanguagesEntity

interface OpenLanguagesDataSource {
    fun setOpenLanguages(value: LanguagesEntity)
    fun getOpenLanguages(): LanguagesEntity
}