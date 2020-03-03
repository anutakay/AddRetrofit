package com.issart.addretrofit.presentation.lookup

import com.issart.addretrofit.usecases.languages.GetOpenLanguages
import com.issart.addretrofit.usecases.lookup.Lookup
import javax.inject.Inject

data class LookupInteractors @Inject constructor(
    val getOpenLanguages: GetOpenLanguages,
    val lookup: Lookup
)