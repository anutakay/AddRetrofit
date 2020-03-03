package com.issart.addretrofit.usecases

import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.entities.LookupEntity

typealias LanguagesResultHandler = (List<LanguagesEntity>) -> Unit
typealias OpenLanguagesHandler = (LanguagesEntity) -> Unit
typealias LookupResultHandler = (LookupEntity) -> Unit