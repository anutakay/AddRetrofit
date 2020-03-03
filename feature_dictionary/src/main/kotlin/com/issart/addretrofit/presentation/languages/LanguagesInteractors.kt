package com.issart.addretrofit.presentation.languages

import com.issart.addretrofit.usecases.languages.GetLanguages
import com.issart.addretrofit.usecases.languages.GetOpenLanguages
import com.issart.addretrofit.usecases.languages.SetOpenLanguages
import javax.inject.Inject

data class LanguagesInteractors @Inject constructor(
    val getLanguages: GetLanguages,
    val setOpenLanguages: SetOpenLanguages,
    val getOpenLanguages: GetOpenLanguages
)