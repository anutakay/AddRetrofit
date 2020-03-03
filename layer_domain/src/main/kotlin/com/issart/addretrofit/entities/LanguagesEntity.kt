package com.issart.addretrofit.entities

data class LanguagesEntity(
    val input: String = "",
    val output: String = ""
) {
    fun isEmpty() = input.isEmpty() && output.isEmpty()
}