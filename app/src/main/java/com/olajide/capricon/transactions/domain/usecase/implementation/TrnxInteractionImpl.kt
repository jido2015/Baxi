package com.olajide.capricon.transactions.domain.usecase.implementation

import com.olajide.capricon.transactions.domain.TrnxRepository
import com.olajide.capricon.transactions.domain.usecase.GetTransactionUseCase
import com.olajide.capricon.transactions.domain.usecase.interaction.TrnxInteraction
import javax.inject.Inject

class TrnxInteractionImpl @Inject constructor(private val repo: TrnxRepository): TrnxInteraction {
    override fun provideTransactionUseCase(): GetTransactionUseCase = GetTransactionUseCase(repo)

}