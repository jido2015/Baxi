package com.olajide.capricon.transactions.domain.usecase

import com.olajide.capricon.transactions.domain.TrnxRepository
import javax.inject.Inject

class GetTrnxUsecase @Inject constructor (private val repository: TrnxRepository){
    suspend operator fun invoke(str : String) = repository.onTransactionReceived(str)
}