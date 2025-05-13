package com.cashi.feescalculation

import com.cashi.feescalculation.domain.Transaction
import com.cashi.feescalculation.domain.TransactionDomainService
import com.cashi.feescalculation.model.TransactionRequest
import com.cashi.feescalculation.model.TransactionResponse
import dev.restate.sdk.annotation.Handler
import dev.restate.sdk.kotlin.Context
import dev.restate.sdk.springboot.RestateService
import kotlin.random.Random

@RestateService
class Payment(
    private val processor: TransactionDomainService = TransactionDomainService(),
) {
    @Handler
    suspend fun processPayment(ctx: Context, request: TransactionRequest): TransactionResponse {

        val transaction = request.toTransactionDomain()


        val processedTransaction = processor.process(transaction)

//
//    // 3. Durable operation
        ctx.run { saveToDatabase(ctx, transaction) }
//

        return TransactionResponse.fromDomain(processedTransaction)
    }

    private suspend fun saveToDatabase(ctx: Context, transaction: Transaction) {
        // This function is to mimic saving to a database
        // it will have 50% chance of failure, to simulate a real-world scenario where restate will handle retries
        if (Random.nextDouble() < 0.5) { // 50% chance of failure
            throw Exception("Failed to save Transaction ${transaction.transactionId} to Database")
        }
        println("Transaction ${transaction.transactionId} saved successfully")
    }
}

