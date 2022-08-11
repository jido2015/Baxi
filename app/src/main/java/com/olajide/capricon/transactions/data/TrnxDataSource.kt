package com.olajide.capricon.transactions.data

import android.util.Log
import com.olajide.capricon.base.Resource
import com.olajide.capricon.transactions.data.model.Transactions
import com.olajide.capricon.transactions.domain.TrnxRepository
import javax.inject.Inject

class TrnxDataSource @Inject constructor (private val api: TrnxApiService): TrnxRepository {
    override suspend fun onTransactionReceived(transactionId: String): Resource<Transactions> {
        return try {
            val response = api.getTransactions(transactionId)

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Log.d("ErrorResponse", response.toString())
                Resource.Success(result)
            }else {
                val errorMessage =  result!!.resp_message
                Resource.Failure(errorMessage)
            }
        } catch (e: Exception) {
            Resource.Exception(e.message ?: "An error occurred")
        }
    }
}