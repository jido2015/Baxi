package com.olajide.capricon.transactions.domain.usecase.interaction

import com.olajide.capricon.transactions.domain.usecase.GetTransactionUseCase

interface TransactionInteraction {
    fun  provideTransactionUseCase(): GetTransactionUseCase
}