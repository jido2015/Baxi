package com.olajide.capricon.transactions.domain.usecase.interaction

import com.olajide.capricon.transactions.domain.usecase.GetTrnxUsecase

interface TrnxInteraction {
    fun  provideTrnxUseCase(): GetTrnxUsecase
}