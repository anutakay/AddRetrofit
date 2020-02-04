package com.issart.addretrofit.framework.device

import android.content.Context
import com.issart.addretrofit.domain.SimpleLanguagesConverter
import java.util.*

class ResourcesLanguageNameDataSource(
    context: Context,
    private val currentLocale: Locale = context.resources.configuration.locales[0]
) : SimpleLanguagesConverter.LanguageNameDataSource {
    override fun getLanguageName(lang: String): String {
        return Locale(lang).getDisplayLanguage(currentLocale).capitalize()
    }
}