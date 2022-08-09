package com.olajide.capricon.login.data

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface LoginApiService {

    @FormUrlEncoded
    @POST("api/core/account/login")
    suspend fun getLoginAccess(
        @Header("Authorization") auth: String,
        @Header("Content-Type") type: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("channel") channel: String,
    ): Response<ResponseBody>

}