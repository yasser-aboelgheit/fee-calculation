package com.cashi.feescalculation.model

import com.cashi.feescalculation.domain.Transaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
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
) {
    fun toTransactionDomain(): Transaction = Transaction(
        transactionId = transactionId,
        amount = BigDecimal(amount.toString()),
        asset = asset,
        assetType = assetType,
        type = type,
        state = state,
        createdAt = createdAt
    )
}