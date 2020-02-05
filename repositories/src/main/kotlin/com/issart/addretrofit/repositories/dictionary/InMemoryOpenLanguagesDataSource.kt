package com.issart.addretrofit.repositories.dictionary

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.datasources.OpenLanguagesDataSource

class InMemoryOpenLanguagesDataSource :
    OpenLanguagesDataSource<LanguagesEntity> {

    private var languages = LanguagesEntity.EMPTY

    override fun setOpenLanguages(value: LanguagesEntity) {
        languages = value
    }

    override fun getOpenLanguages(): LanguagesEntity = languages
}