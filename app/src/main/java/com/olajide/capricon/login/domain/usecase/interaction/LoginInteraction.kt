package com.olajide.capricon.login.domain.usecase.interaction

import com.olajide.capricon.login.domain.usecase.GetLoginStatusUseCase

interface LoginInteraction {
    fun  provideLoginUseCase(): GetLoginStatusUseCase
}