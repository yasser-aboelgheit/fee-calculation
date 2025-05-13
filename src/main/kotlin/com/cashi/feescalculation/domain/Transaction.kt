package com.cashi.feescalculation.domain

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class Transaction(
    val transactionId: String,
    @Contextual
    val amount: BigDecimal,
    val asset: String,
    val assetType: String,
    val type: String,
    var state: String,
    @Contextual
    var fee: BigDecimal? = null,
    @Contextual
    var rate: BigDecimal? = null,
    val createdAt: String,
)
