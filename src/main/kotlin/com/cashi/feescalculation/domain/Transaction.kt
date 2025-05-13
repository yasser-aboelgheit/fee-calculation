package com.cashi.feescalculation.domain

import com.cashi.feescalculation.model.TransactionRequest
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val transactionId: String,
    val amount: Double,
    val asset: String,
    val assetType: String,
    val type: String,
    var state: String,
    val createdAt: String,
)
