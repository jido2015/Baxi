package com.olajide.capricon.transactions.domain.usecase.implementation

import com.olajide.capricon.transactions.domain.TrnxRepository
import com.olajide.capricon.transactions.domain.usecase.GetTrnxUsecase
import com.olajide.capricon.transactions.domain.usecase.interaction.TrnxInteraction
import javax.inject.Inject

class TrnxInteractionImpl @Inject constructor (private val repository : TrnxRepository):
    TrnxInteraction {
    override fun provideTrnxUseCase(): GetTrnxUsecase = GetTrnxUsecase(repository)

}