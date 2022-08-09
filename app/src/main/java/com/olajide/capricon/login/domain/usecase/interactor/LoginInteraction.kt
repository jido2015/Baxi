package com.olajide.capricon.login.domain.usecase.interactor

import com.olajide.capricon.login.domain.usecase.GetLoginStatusUseCase

interface LoginInteraction {
    fun  provideLoginUseCase(): GetLoginStatusUseCase
}