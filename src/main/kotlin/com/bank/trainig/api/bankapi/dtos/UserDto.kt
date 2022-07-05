package com.bank.trainig.api.bankapi.dtos

import com.bank.trainig.api.bankapi.model.AccountType

data class UserDto(val userId: String, val userName: String, val accountType: AccountType)
