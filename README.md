# Fee Calculation Service

A durable transaction fee processing system built with [Restate](https://restate.dev/) for reliable execution and automatic retries.

## Features

- **Transaction Processing**
    - Mobile Top Up (0.15% fee)
    - Card Top Up (0.35% fee)
    - Default (2% fee)
- **Durable Workflows**
    - Automatic retries on failures
    - State persistence across retries
- **Validation**
    - Amount must be positive
    - Transaction state tracking

## Technologies Used

- **Spring Boot** — for building the RESTful service and dependency management
- **Kotlin** — modern, expressive JVM language used for implementation
- **Restate** — to manage durable workflows with automatic retries and state persistence


## Getting Started

### Prerequisites

- Java 17+

### Installation

Clone the repository:
 d
```bash
git clone https://github.com/yasser-aboelgheit/fee-calculation.git
```

Start Restate runtime:

```bash
restate server
```

### Running the Service

```bash
./gradlew bootRun
```

### Register the service on Restate

```bash
restate deployments register http://localhost:9080
```

## API Reference

### Process Transaction

**Request:**

```http
POST /Payment/process
Content-Type: application/json
```

```json

{"transaction_id": "txn_001",
  "amount": 1000,
  "asset": "USD",
  "asset_type": "FIAT",
  "type": "card top up",
  "state": "SETTLED - PENDING FEE",
  "created_at": "2023-08-3015:42:17.610059"
}

```

**Response:**

```json
{
  "transactionId": "txn_001",
  "amount": 1000.0,
  "asset": "USD",
  "type": "card top up",
  "fee": 3.5,
  "rate": 0.0035,
  "description": "Standard fee rate of 0.0035%"
}
```

## Testing

Run unit tests:

```bash
./gradlew test
```

### Key test scenarios:

- Mobile Top Up fee calculation
- Card Top Up fee calculation
- Retry behavior verification


## Retry Behavior

The service implements smart retries:

- attempt to save transaction to DB have 50% success probability
- Maximum 10 retries configured

**Note** : There is no Database, it is simulated behaviour that prints success or raise error based on random values.