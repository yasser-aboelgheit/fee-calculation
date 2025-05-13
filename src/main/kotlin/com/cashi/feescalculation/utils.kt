import com.cashi.restatestarter.model.Transaction
import com.cashi.restatestarter.model.TransactionResponse

fun sendNotification(greetingId: String, name: String) {
    if (Math.random() < 0.5) { // 50% chance of failure
        println("👻 Failed to send notification: $greetingId - $name")
        throw RuntimeException("Failed to send notification: $greetingId - $name")
    }
    println("Notification sent: $greetingId - $name")
}

fun sendReminder(greetingId: String) {
    if (Math.random() < 0.5) { // 50% chance of failure
        println("👻 Failed to send reminder: $greetingId")
        throw RuntimeException("Failed to send reminder: $greetingId")
    }
    println("Reminder sent: $greetingId")
}


fun calculateFee(tx: Transaction): TransactionResponse {
    val rate = 0.0015
    val fee = tx.amount * rate
    println("INSIDE CALCULATE FEE")
    return fee
    return mapOf(
        "transaction_id" to tx.transactionId,
        "amount" to tx.amount,
        "asset" to tx.asset,
        "type" to tx.type,
        "fee" to fee,
        "rate" to rate,
        "description" to "Standard fee rate of 0.15%"
    )
}
