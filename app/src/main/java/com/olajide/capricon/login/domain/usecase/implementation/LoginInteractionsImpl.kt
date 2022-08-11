package com.olajide.capricon.login.domain.usecase.implementation

import com.olajide.capricon.login.domain.Repository
import com.olajide.capricon.login.domain.usecase.GetLoginStatusUseCase
import com.olajide.capricon.login.domain.usecase.interaction.LoginInteraction
import javax.inject.Inject

class LoginInteractionsImpl @Inject constructor (private val repository : Repository):
    LoginInteraction {
    override fun provideLoginUseCase():
    GetLoginStatusUseCase = GetLoginStatusUseCase(repository)
}