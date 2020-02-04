package com.issart.addretrofit.data.datasources

import com.issart.addretrofit.domain.Languages

interface OpenLanguagesDataSource {
    fun setOpenLanguages(value: Languages)
    fun getOpenLanguages(): Languages
}