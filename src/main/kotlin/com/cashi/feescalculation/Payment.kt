package com.cashi.feescalculation

import dev.restate.sdk.kotlin.*
import dev.restate.sdk.annotation.Handler
import dev.restate.sdk.springboot.RestateService
import com.cashi.feescalculation.domain.TransactionDomainService
import com.cashi.feescalculation.model.TransactionRequest
import com.cashi.feescalculation.model.TransactionResponse

@RestateService
class Payment (
  private val processor: TransactionDomainService = TransactionDomainService(),
){
  @Handler
  suspend fun processPayment(ctx: Context, request: TransactionRequest): TransactionResponse {

    val transaction = request.toTransactionDomain()


    val processedTransaction = processor.process(transaction)

//
//    // 3. Durable operation
//    ctx.run { saveToDatabase(transaction) }
//

    return TransactionResponse.fromDomain(transaction)
//  }
//
//  private suspend fun saveToDatabase(tx: Transaction) {
//    // Durably save transaction
//  }
  }
}
