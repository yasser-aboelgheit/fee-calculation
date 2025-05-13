package com.cashi.feescalculation

import com.cashi.feescalculation.model.TransactionRequest
import dev.restate.client.Client
import dev.restate.sdk.testing.BindService
import dev.restate.sdk.testing.RestateClient
import dev.restate.sdk.testing.RestateTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [Payment::class])
@RestateTest
class TransactionProcessTest {

    @Autowired
    @BindService
    lateinit var service: Payment

    @Test
    @Timeout(10)
    fun `process Mobile Top Up transaction`(@RestateClient ingressClient: Client) = runTest {
        // Given
        val client = PaymentClient.fromClient(ingressClient)
        val tx = TransactionRequest(
            type = "Mobile Top Up", amount = 100.0, asset = "USD",
            assetType = "CASH", transactionId = "tx123", state = "PENDING", createdAt = "2023-10-01T00:00:00Z"
        )

        // When
        val result = client.processPayment(tx)

        // Then
        assertThat(result.fee).isEqualTo(0.15) // 100 * 0.0015
    }

    @Test
    @Timeout(10)
    fun `process Card Top Up transaction`(@RestateClient ingressClient: Client) = runTest {
        val client = PaymentClient.fromClient(ingressClient)
        // Given
        val tx = TransactionRequest(
            type = "Card Top Up", amount = 100.0, asset = "USD",
            assetType = "CASH", transactionId = "tx123", state = "PENDING", createdAt = "2023-10-01T00:00:00Z"
        )

        // When
        val result = client.processPayment(tx)

        // Then
        assertThat(result.fee).isEqualTo(0.35) // 100 * 0.0035
    }
}