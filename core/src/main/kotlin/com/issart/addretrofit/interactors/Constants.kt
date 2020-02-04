package com.issart.addretrofit.interactors

import com.issart.addretrofit.domain.Languages
import com.issart.addretrofit.domain.LookupResult

typealias ErrorHandler = (String) -> Unit
typealias LanguagesResultHandler = (List<Languages>) -> Unit
typealias LookupResultHandler = (LookupResult) -> Unit