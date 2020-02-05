package com.issart.addretrofit.framework.network

import com.issart.addretrofit.LookupEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {

    @GET("getLangs")
    suspend fun getLangs(@Query("key") key: String): Response<List<String>>

    @GET("lookup")
    suspend fun lookup(
        @Query("key") key: String,
        @Query("lang") lang: String,
        @Query("text") word: String
    ): Response<LookupEntity>
}