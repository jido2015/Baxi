package com.olajide.capricon.transactions.data.model

data class Agent(
    val category_id: Any,
    val created_at: String,
    val device_owner_id: Int,
    val id: String,
    val outlet_id: Any,
    val primary_user_id: Any,
    val territory_id: Any,
    val trader_type: String,
    val updated_at: String
)