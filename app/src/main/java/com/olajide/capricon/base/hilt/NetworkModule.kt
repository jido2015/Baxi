package com.olajide.capricon.base.hilt

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.olajide.capricon.base.Constants
import com.olajide.capricon.base.provideGenericApiService
import com.olajide.capricon.base.qualifiers.AuthInterceptorOkHttpClient
import com.olajide.capricon.base.qualifiers.OtherInterceptorOkHttpClient
import com.olajide.capricon.login.data.ApiService
import com.olajide.capricon.login.data.LoginObject
import com.olajide.capricon.transactions.data.TrnxApiService
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
    fun provideHttpClientWithAuthIntercept(application: Application): OkHttpClient {
        val pref: SharedPreferences = application.getSharedPreferences("com.olajide.capricon", Context.MODE_PRIVATE)
        val token =pref.getString("token", null)

        Log.d("LogAccessToken", "$token")
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
    fun provideApiService(@OtherInterceptorOkHttpClient okHttpClient: OkHttpClient): ApiService =
        provideGenericApiService(okHttpClient)

    @Singleton
    @Provides
    fun provideTrnxApiService(@AuthInterceptorOkHttpClient okHttpClient: OkHttpClient): TrnxApiService =
        provideGenericApiService(okHttpClient)
}