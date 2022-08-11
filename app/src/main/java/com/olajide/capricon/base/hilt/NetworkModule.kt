package com.olajide.capricon.base.hilt

import com.olajide.capricon.Constants
import com.olajide.capricon.base.provideGenericApiService
import com.olajide.capricon.base.qualifiers.AuthInterceptorOkHttpClient
import com.olajide.capricon.base.qualifiers.OtherInterceptorOkHttpClient
import com.olajide.capricon.login.data.LoginApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OtherInterceptorOkHttpClient
    @Singleton
    @Provides
    fun provideHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    @AuthInterceptorOkHttpClient
    @Singleton
    @Provides
    fun provideHttpClientWithAuthIntercept(token: String): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(Constants.AUTHORIZATION, "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(@OtherInterceptorOkHttpClient okHttpClient: OkHttpClient): LoginApiService =
        provideGenericApiService(okHttpClient)
}