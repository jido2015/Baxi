package com.olajide.capricon.hilt.base

import com.olajide.capricon.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** [provideGenericApiService] is a generic function for exposing retrofit API service
 * instead of repeatedly creating API service, just specify the type and it be created
 * */
inline fun <reified T> provideGenericApiService(
    okHttpClient: OkHttpClient
): T {
    return try {
        Retrofit.Builder().client(okHttpClient)
            .baseUrl("${BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(T::class.java)
    }catch (e:IllegalArgumentException){
        e.printStackTrace()
        throw IllegalArgumentException("The Specified type has to be an interface with retrofit api calls")
    }
}