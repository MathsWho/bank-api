package com.bank.trainig.api.bankapi.model

data class User(
    val id: String,
    val accountNumber: String,
    val accountType: AccountType,
    val login: String,
    val password: String,
    val fee: Int,
    val ts: String,
    val userName: String
)
