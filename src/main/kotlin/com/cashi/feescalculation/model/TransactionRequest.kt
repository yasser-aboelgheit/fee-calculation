package com.cashi.restatestarter.model

import kotlinx.serialization.SerialName

data class TransactionRequest(
    @SerialName("transaction_id")
    val transactionId: String,
    val amount: Double,
    val asset: String,
    @SerialName("asset_type")
    val assetType: String,
    val type: String,
    val state: String,
    @SerialName("created_at")
    val createdAt: String,
)
