package com.issart.addretrofit.framework.device

import android.content.Context
import com.issart.addretrofit.datasources.LanguageNameDataSource
import java.util.*

class ResourcesLanguageNameDataSource(
    context: Context,
    private val currentLocale: Locale = context.resources.configuration.locale
) : LanguageNameDataSource {
    override fun getLanguageName(lang: String): String {
        return Locale(lang).getDisplayLanguage(currentLocale).capitalize()
    }
}