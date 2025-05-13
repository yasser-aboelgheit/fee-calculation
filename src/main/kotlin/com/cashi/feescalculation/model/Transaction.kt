package com.cashi.restatestarter.model
import kotlinx.serialization.Serializable


@Serializable
data class Transaction(
    val transactionId: String,
    val amount: Double,
    val asset: String,
    val assetType: String,
    val type: String,
    val state: String,
    val createdAt: String,
) {
    companion object{
        fun fromTransactionRequest(transactionRequest: TransactionRequest): Transaction {
            return Transaction(
                transactionId = transactionRequest.transactionId,
                amount = transactionRequest.amount,
                asset = transactionRequest.asset,
                assetType = transactionRequest.assetType,
                type = transactionRequest.type,
                state = transactionRequest.state,
                createdAt = transactionRequest.createdAt
            )
        }
}
}
