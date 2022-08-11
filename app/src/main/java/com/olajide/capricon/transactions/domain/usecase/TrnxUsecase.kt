package com.olajide.capricon.transactions.domain.usecase

import com.olajide.capricon.transactions.domain.TrnxRepository
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor (private val repo: TrnxRepository) {
    suspend operator fun invoke(id : String) = repo.onTransactionReceived(id)

}