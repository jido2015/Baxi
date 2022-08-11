package com.olajide.capricon.transactions.data.model

data class Transactions(
    val data: Data,
    val resp_code: String,
    val resp_description: String,
    val resp_message: String
)