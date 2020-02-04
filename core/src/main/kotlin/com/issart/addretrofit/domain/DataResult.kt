package com.issart.addretrofit.domain

sealed class DataResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class SpecificError(val error: ErrorEnum, val message: String?) : DataResult<Nothing>()
    data class CommonError(val exception: Exception) : DataResult<Nothing>()
}

enum class ErrorEnum {
    SPECIFIC_RESPONSE_ERROR
}