package com.olajide.capricon.transactions.data.model

data class DataX(
    val agent: Agent,
    val agent_code: String,
    val agent_device_id: Int,
    val agent_fullname: String,
    val agent_id: String,
    val agent_partner_type_id: Int,
    val agent_username: String,
    val amount_paid: Long,
    val channel: Channel,
    val channel_id: Int,
    val created_at: String,
    val debited_wallet: String,
    val device_type_id: Int,
    val id: Int,
    val latitude: String,
    val longitude: String,
    val partner_type: String,
    val payment_method: Int,
    val payment_status: Int,
    val request_id: String,
    val source_model: String,
    val status: String,
    val total_commission: Int,
    val total_fee: Int,
    val transaction_amount: Long,
    val transaction_date: String,
    val transaction_description: String,
    val transaction_ref: String,
    val transaction_service: TransactionService,
    val transaction_service_id: String,
    val transaction_status_message: String,
    val transaction_type: TransactionType,
    val transaction_type_id: Int,
    val updated_at: String,
    val value_given: Boolean
)