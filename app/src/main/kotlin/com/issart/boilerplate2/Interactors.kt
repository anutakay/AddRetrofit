package com.issart.boilerplate2

import com.issart.boilerplate2.interactors.GetMessage
import com.issart.boilerplate2.interactors.GetSecondMessage

data class Interactors(
    val getMessage: GetMessage,
    val getSecondMessage: GetSecondMessage
)