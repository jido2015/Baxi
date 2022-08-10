package com.olajide.capricon.login.data

data class TokenData(
    val expirer_in: String,
    val refresh_token: String,
    val token: String
)