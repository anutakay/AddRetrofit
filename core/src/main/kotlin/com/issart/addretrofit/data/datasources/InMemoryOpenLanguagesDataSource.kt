package com.issart.addretrofit.data.datasources

import com.issart.addretrofit.domain.Languages

class InMemoryOpenLanguagesDataSource : OpenLanguagesDataSource {

    private var languages = Languages.EMPTY

    override fun setOpenLanguages(value: Languages) {
        languages = value
    }

    override fun getOpenLanguages(): Languages = languages
}