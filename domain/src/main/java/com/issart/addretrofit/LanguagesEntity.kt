package com.issart.addretrofit

data class LanguagesEntity(
    val input: String,
    val inputName: String,
    val output: String,
    val outputName: String
) {
    companion object {
        val EMPTY =
            LanguagesEntity("", "EMPTY", "", "EMPTY")
    }
}