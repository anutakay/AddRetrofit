package com.issart.addretrofit.presentation

import android.content.Context
import java.util.*

class ResourcesLanguageNameDataSource(
    context: Context,
    private val currentLocale: Locale = context.resources.configuration.locale
) {
    fun getLanguageName(lang: String): String =
        Locale(lang).getDisplayLanguage(currentLocale).capitalize()
}