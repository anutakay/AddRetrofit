package com.issart.addretrofit.presentation

import android.content.Context
import android.content.Intent

object IntentUtil {
    const val CHOOSE_INPUT_LANGUAGE = 1
    const val CHOOSE_OUTPUT_LANGUAGE = 2

    const val LANGUAGE_EXTRA = "language_extra"
    const val INPUT_LANGUAGE = 1
    const val OUTPUT_LANGUAGE = 2

    fun chooseInputLanguageIntent(context: Context) =
        Intent(context, com.issart.addretrofit.presentation.languages.LanguagesActivity::class.java).putExtra(
            LANGUAGE_EXTRA,
            INPUT_LANGUAGE
        )

    fun chooseOutputLanguageIntent(context: Context) =
        Intent(context, com.issart.addretrofit.presentation.languages.LanguagesActivity::class.java).putExtra(
            LANGUAGE_EXTRA,
            OUTPUT_LANGUAGE
        )
}