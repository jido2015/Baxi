package com.olajide.capricon.transactions.domain.usecase.implementation

import com.olajide.capricon.transactions.domain.TrnxRepository
import com.olajide.capricon.transactions.domain.usecase.GetTransactionUseCase
import com.olajide.capricon.transactions.domain.usecase.interaction.TransactionInteraction
import javax.inject.Inject

class TrnxInteractionImpl @Inject constructor(private val repo: TrnxRepository): TransactionInteraction {
    override fun provideTransactionUseCase(): GetTransactionUseCase = GetTransactionUseCase(repo)

}