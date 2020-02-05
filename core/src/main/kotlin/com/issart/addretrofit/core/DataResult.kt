package com.issart.addretrofit.core

sealed class DataResult<out ENTITY : Any> {
    data class Success<out ENTITY : Any>(val data: ENTITY) : DataResult<ENTITY>()
    data class CommonError(val exception: Exception) : DataResult<Nothing>()
    data class SpecificError<ERROR : Any>(
        val error: ERROR,
        val message: String?
    ) : DataResult<Nothing>()
}