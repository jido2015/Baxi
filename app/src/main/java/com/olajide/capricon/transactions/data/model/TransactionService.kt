package com.olajide.capricon.transactions.data.model

data class TransactionService(
    val amount_limit: Any,
    val created_at: String,
    val description: String,
    val id: String,
    val service_name: String,
    val transaction_type_id: Int,
    val updated_at: String
)