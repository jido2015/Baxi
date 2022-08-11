package com.olajide.capricon.transactions.data

import android.util.Log
import com.olajide.capricon.base.Resource
import com.olajide.capricon.transactions.data.model.Transactions
import com.olajide.capricon.transactions.domain.TrnxRepository
import javax.inject.Inject

class TrnxDataSource @Inject constructor (private val api: TrnxApiService): TrnxRepository {
    override suspend fun onTransactionReceived(trnxId: String): Resource<Transactions> {
        return try {
            val response = api.getTransactions(trnxId)

            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            }else {
                Log.d("ErrorResponse", response.toString())
                val errorMessage =  result!!.resp_message
                Resource.Failure(errorMessage)
            }
        } catch (e: Exception) {
            Resource.Exception(e.message ?: "An error occurred")
        }
    }
}