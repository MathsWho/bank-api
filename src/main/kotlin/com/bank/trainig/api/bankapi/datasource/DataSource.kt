package com.bank.trainig.api.bankapi.datasource

import com.bank.trainig.api.bankapi.dtos.*
import com.bank.trainig.api.bankapi.model.User
import java.util.*

interface DataSource {
    fun registerUser(user: RegisterUserDto): UserDto
    fun login(login: LoginDto): UserDto
    fun updateUserFee(user:UpdateFeeDto , userIdThatWillChangeFee: String)
    fun getUserById(userId: String): UserDto
    fun getUsers(): Collection<UserDetailsDto>
    fun getUserDetailsById(userId: String): UserDetailsDto
}