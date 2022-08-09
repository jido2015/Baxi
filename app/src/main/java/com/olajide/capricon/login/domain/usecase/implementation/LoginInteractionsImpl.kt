package com.olajide.capricon.login.domain.usecase.implementation

import com.olajide.capricon.login.domain.LoginRepository
import com.olajide.capricon.login.domain.usecase.GetLoginStatusUseCase
import com.olajide.capricon.login.domain.usecase.interactor.LoginInteraction
import javax.inject.Inject

class LoginInteractionsImpl @Inject constructor (private val repository : LoginRepository):
    LoginInteraction {
    override fun provideLoginUseCase():
    GetLoginStatusUseCase = GetLoginStatusUseCase(repository)
}