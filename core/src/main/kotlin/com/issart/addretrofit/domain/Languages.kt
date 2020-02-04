package com.issart.addretrofit.domain

data class Languages(
    val input: String,
    val inputName: String,
    val output: String,
    val outputName: String
) {
    companion object {
        val EMPTY = Languages("", "EMPTY", "", "EMPTY")
    }
}