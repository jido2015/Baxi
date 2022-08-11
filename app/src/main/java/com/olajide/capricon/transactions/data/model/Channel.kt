package com.olajide.capricon.transactions.data.model

data class Channel(
    val channel_code: String,
    val created_at: Any,
    val deleted_at: Any,
    val description: String,
    val id: Int,
    val is_enabled: Boolean,
    val name: String,
    val updated_at: Any
)