package com.issart.addretrofit.data.datasources

import com.issart.addretrofit.entities.LanguagesEntity

class InMemoryOpenLanguagesDataSource(
    private var languages: LanguagesEntity
) : OpenLanguagesDataSource<LanguagesEntity> {

    override fun setOpenLanguages(value: LanguagesEntity) {
        languages = value
    }

    override fun getOpenLanguages(): LanguagesEntity = languages
}