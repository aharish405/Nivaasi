package com.nivaasi.app.data.model

data class Transaction(
    val id: String,
    val type: TransactionType,
    val amount: Double,
    val date: String,
    val status: TransactionStatus,
    val description: String
)

enum class TransactionType {
    RENT,
    DEPOSIT,
    EXPENSE
}

enum class TransactionStatus {
    COLLECTED,
    PENDING
}
