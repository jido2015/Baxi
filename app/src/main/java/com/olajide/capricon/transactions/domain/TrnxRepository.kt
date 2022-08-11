package com.olajide.capricon.transactions.domain

import com.olajide.capricon.base.Resource
import com.olajide.capricon.transactions.data.model.Transactions

interface TrnxRepository {
    suspend fun onTransactionReceived(transactionId: String): Resource<Transactions>
}