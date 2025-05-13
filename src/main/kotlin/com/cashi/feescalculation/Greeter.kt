package com.cashi.feescalculation

import com.cashi.feescalculation.domain.Transaction
import dev.restate.sdk.kotlin.*
import dev.restate.sdk.annotation.Handler
import dev.restate.sdk.springboot.RestateService
import org.springframework.beans.factory.annotation.Value
import sendNotification
import sendReminder
import kotlin.time.Duration.Companion.seconds
import calculateFee
import com.cashi.feescalculation.model.TransactionRequest
import com.cashi.feescalculation.model.TransactionResponse

@RestateService
class Greeter {

  @Value("\${greetingPrefix}")
  lateinit var greetingPrefix: String

  @Handler
  suspend fun greet(ctx: Context, name: String): String {
    // Durably execute a set of steps; resilient against failures
    val greetingId = ctx.random().nextUUID().toString()
    ctx.runBlock { sendNotification(greetingId, name) }
    ctx.sleep(1.seconds)
    ctx.runBlock { sendReminder(greetingId) }

    // Respond to caller
    return "You said $greetingPrefix to $name!"
  }

  @Handler
  suspend fun calculateFee(ctx: Context, request: TransactionRequest): String {

//    val transaction = ctx.runBlock { calculateFee(Transaction.fromTransactionRequest(transactionRequest)) }

//    // 1. Simple mapping (companion)
    val transaction = request.toTransactionDomain()
//
//    // 2. Business logic (pure)
    val fee = processor.process(transaction)
//
//    // 3. Durable operation
//    ctx.run { saveToDatabase(transaction) }
//
//    // 4. Complex mapping (dedicated mapper)
//    return responseMapper.toResponse(transaction, fee)
//  }
//
//  private suspend fun saveToDatabase(tx: Transaction) {
//    // Durably save transaction
//  }
    return "transaction"
  }
}
