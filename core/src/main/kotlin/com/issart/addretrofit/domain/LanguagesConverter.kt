package com.issart.addretrofit.domain

interface LanguagesConverter {
    fun fromShortcut(value: String): Languages
    fun toShortcut(value: Languages): String
}