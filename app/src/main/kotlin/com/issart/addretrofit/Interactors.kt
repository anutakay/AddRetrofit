package com.issart.addretrofit

import com.issart.addretrofit.interactors.languages.GetLanguages
import com.issart.addretrofit.interactors.languages.GetOpenLanguages
import com.issart.addretrofit.interactors.languages.SetOpenLanguages
import com.issart.addretrofit.interactors.lookup.Lookup

data class Interactors(
    val getLanguages: GetLanguages,
    val setOpenLanguages: SetOpenLanguages,
    val getOpenLanguages: GetOpenLanguages,
    val lookup: Lookup
)