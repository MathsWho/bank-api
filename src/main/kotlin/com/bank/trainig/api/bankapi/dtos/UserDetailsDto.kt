package com.bank.trainig.api.bankapi.dtos

import com.bank.trainig.api.bankapi.model.AccountType

data class UserDetailsDto(val userId: String, val userName: String, val accountType: AccountType, val fee: Int)
