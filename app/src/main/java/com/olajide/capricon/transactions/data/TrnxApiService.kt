package com.olajide.capricon.transactions.data

import com.olajide.capricon.transactions.data.model.Transactions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrnxApiService {

    @GET("https://api.dev.baxi-services.com/api/transactions/v1/transactions?")
    suspend fun getTransactions(@Query("page") productId: String): Response<Transactions>
}