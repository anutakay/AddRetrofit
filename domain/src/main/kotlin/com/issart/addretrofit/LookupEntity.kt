package com.issart.addretrofit

data class LookupEntity(
    val head: Head,
    val def: List<Definition>
)

data class Definition(
    val text: String,
    val pos: String,
    val ts: String,
    val tr: List<Translation>
)

data class Translation(
    val text: String,
    val pos: String,
    val gen: String
)

class Head
