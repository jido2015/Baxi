package com.olajide.capricon.login.data

data class Data(
    val agent_code: String,
    val email: String,
    val first_name: String,
    val gender: String,
    val id: String,
    val last_name: String,
    val other_name: String,
    val phone_number: String,
    val token_data: TokenData,
    val username: String
)