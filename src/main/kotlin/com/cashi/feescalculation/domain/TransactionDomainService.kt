package com.cashi.feescalculation.domain


class TransactionDomainService(private val feeRate: Double = 0.02 // 2% fee
) {
    fun process(tx: Transaction): Double {
        require(tx.amount > 0) { "Amount must be positive" }

        tx.state = TransactionStatus.SETTLED_PENDING_FEE.toString()
        return tx.amount * feeRate
    }
}

