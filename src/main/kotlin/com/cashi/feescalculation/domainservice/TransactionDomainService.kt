package com.cashi.feescalculation.domainservice

import com.cashi.feescalculation.model.Transaction

enum class TransactionStatus {
    PENDING, SETTLED_PENDING_FEE, COMPLETED, FAILED
}

class TransactionDomainService(private val feeRate: Double = 0.02 // 2% fee
) {
    fun process(tx: Transaction): Double {
        require(tx.amount > 0) { "Amount must be positive" }
//
        tx.state = TransactionStatus.SETTLED_PENDING_FEE.toString()
        return tx.amount * feeRate
    }
}

