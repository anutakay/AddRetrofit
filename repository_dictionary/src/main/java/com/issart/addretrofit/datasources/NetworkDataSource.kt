package com.issart.addretrofit.datasources

import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.core.ErrorEnum
import retrofit2.Response

open class NetworkDataSource {
    protected suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String? = null
    ): DataResult<T> = try {
        safeNetworkCall(call, errorMessage)
    } catch (e: Exception) {
        DataResult.CommonError(e)
    }

    private suspend fun <T : Any> safeNetworkCall(
        call: suspend () -> Response<T>,
        errorMessage: String?
    ): DataResult<T> {
        val response = call.invoke()
        return when (response.isSuccessful) {
            true ->
                when (response.body() != null) {
                    true -> DataResult.Success(response.body()!!)
                    else -> DataResult.SpecificError(
                        ErrorEnum.EMPTY_BODY_RESPONSE_ERROR,
                        errorMessage
                    )
                }
            else -> DataResult.SpecificError(ErrorEnum.SPECIFIC_RESPONSE_ERROR, errorMessage)
        }
    }
}