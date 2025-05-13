package com.cashi.feescalculation.domain

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TransactionDomainService {
    fun process(tx: Transaction): Transaction {
        validateTransaction(tx)
        updateTransactionState(tx, TransactionStatus.SETTLED_PENDING_FEE)
        tx.fee =  calculateFee(tx)
        tx.rate = getRateForType(tx.type)
        return tx
    }

    fun calculateFee(tx: Transaction): BigDecimal {
        return tx.amount * getRateForType(tx.type)
    }

    private fun getRateForType(type: String): BigDecimal {
        return when (type.lowercase()) {
            // here it should be a singleton model on the DB with giving admin
            // dashboard ability to change it since it is pruned to frequent change
            "mobile top up" -> BigDecimal("0.0015")
            "card top up" -> BigDecimal("0.0035")
            else -> BigDecimal("0.02")
        }
    }

    private fun validateTransaction(tx: Transaction) {
        require(tx.amount > BigDecimal("0.00")) { "Amount must be positive" }
    }

    private fun updateTransactionState(tx: Transaction, status: TransactionStatus) {
        tx.state = status.toString()
    }
}
