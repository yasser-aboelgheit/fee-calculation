package com.cashi.feescalculation

import com.cashi.feescalculation.model.TransactionRequest
import dev.restate.client.Client
import dev.restate.sdk.testing.BindService
import dev.restate.sdk.testing.RestateClient
import dev.restate.sdk.testing.RestateTest
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
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
    private lateinit var client: PaymentClient.IngressClient

    @BeforeEach
    fun setup(@RestateClient ingressClient: Client) {
        client = PaymentClient.fromClient(ingressClient)
    }

    private fun baseTransaction(
        type: String,
        amount: Double = 100.0
    ): TransactionRequest = TransactionRequest(
        type = type,
        amount = amount,
        asset = "USD",
        assetType = "CASH",
        transactionId = "tx123",
        state = "PENDING",
        createdAt = "2023-10-01T00:00:00Z"
    )
    @Test
    @Timeout(10)
    fun `process Mobile Top Up transaction`() = runTest {
        // Given
        val tx = baseTransaction(
            type = "Mobile Top Up", amount = 100.0
        )

        // When
        val result = client.processPayment(tx)

        // Then
        assertThat(result.fee).isEqualTo(0.15) // 100 * 0.0015
    }

    @Test
    @Timeout(10)
    fun `process Card Top Up transaction`() = runTest {
        // Given
        val tx = baseTransaction(
            type = "Card Top Up", amount = 100.0
        )

        // When
        val result = client.processPayment(tx)

        // Then
        assertThat(result.fee).isEqualTo(0.35) // 100 * 0.0035
    }
}
