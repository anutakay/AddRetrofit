package com.issart.addretrofit.datasources

import com.issart.addretrofit.LanguagesEntity

class InMemoryOpenLanguagesDataSource : OpenLanguagesDataSource {

    private var languages = LanguagesEntity.EMPTY

    override fun setOpenLanguages(value: LanguagesEntity) {
        languages = value
    }

    override fun getOpenLanguages(): LanguagesEntity = languages
}