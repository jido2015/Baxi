package com.olajide.capricon.login.domain.usecase

import com.olajide.capricon.login.data.LoginObject
import com.olajide.capricon.login.domain.LoginRepository
import javax.inject.Inject

class GetLoginStatusUseCase @Inject constructor (private val repository: LoginRepository){
    suspend operator fun invoke(obj : LoginObject) = repository.onUserLogin(obj)
}