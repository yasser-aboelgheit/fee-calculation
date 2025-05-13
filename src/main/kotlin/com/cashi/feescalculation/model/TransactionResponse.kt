package com.cashi.feescalculation.model

import com.cashi.feescalculation.domain.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val transactionId: String,
    val amount: Double,
    val asset: String,
    val type: String,
    val fee: Double?,
    val rate: Double?,
    val description: String
) {
    companion object {
        fun fromDomain(
            tx: Transaction,
            description: String = "Standard fee rate of ${tx.rate}%"
        ): TransactionResponse {
            return TransactionResponse(
                transactionId = tx.transactionId,
                amount = tx.amount.toDouble(),
                asset = tx.asset,
                type = tx.type,
                fee = tx.fee?.toDouble(),
                rate = tx.rate?.toDouble(),
                description = description
            )
        }
    }
}
