package com.olajide.capricon.login.data

import retrofit2.Response
import retrofit2.http.*

interface LoginApiService {

    @POST("api/core/account/login")
    suspend fun getLoginAccess(
        @Body loginObject: LoginObject
    ): Response<ResponseContent>

}