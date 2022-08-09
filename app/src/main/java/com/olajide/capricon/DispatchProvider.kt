package com.olajide.capricon

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

}