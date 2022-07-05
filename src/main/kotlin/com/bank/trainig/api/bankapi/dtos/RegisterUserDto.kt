package com.bank.trainig.api.bankapi.dtos

import com.bank.trainig.api.bankapi.model.AccountType

data class RegisterUserDto(val login: String, val password: String, val userName: String, val accountType: AccountType)
