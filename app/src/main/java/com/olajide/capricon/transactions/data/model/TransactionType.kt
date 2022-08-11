package com.olajide.capricon.transactions.data.model

data class TransactionType(
    val created_at: String,
    val deleted_at: Any,
    val description: String,
    val id: Int,
    val is_enabled: Boolean,
    val name: String,
    val updated_at: String
)