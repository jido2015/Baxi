package com.olajide.capricon.login.data

data class ResponseContent(
    val data: Data,
    val resp_code: String,
    val resp_description: String,
    val resp_message: String
)