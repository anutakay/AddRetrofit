package com.issart.addretrofit.framework.network

import android.util.Log
import com.issart.addretrofit.core.DataResult
import com.issart.addretrofit.framework.ErrorEnum
import retrofit2.Response

open class NetworkDataSource {
    protected suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String? = null
    ): DataResult<T> = try {
        safeNetworkCall(call, errorMessage)
    } catch (e: Exception) {
        Log.e("NetworkDataSource", "$errorMessage\n ${e::class.java.name}: ${e.message}")
        DataResult.CommonError(e)
    }

    private suspend fun <T : Any> safeNetworkCall(
        call: suspend () -> Response<T>,
        errorMessage: String?
    ): DataResult<T> {
        val response = call.invoke()
        return when (response.isSuccessful) {
            true -> DataResult.Success(response.body()!!)
            else -> DataResult.SpecificError(ErrorEnum.SPECIFIC_RESPONSE_ERROR, errorMessage)
        }
    }
}