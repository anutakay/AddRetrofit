package com.issart.addretrofit.interactors

import com.issart.addretrofit.LanguagesEntity
import com.issart.addretrofit.LookupEntity

typealias LanguagesResultHandler = (List<LanguagesEntity>) -> Unit
typealias LookupResultHandler = (LookupEntity) -> Unit