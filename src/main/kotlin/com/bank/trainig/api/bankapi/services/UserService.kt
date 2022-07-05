package com.bank.trainig.api.bankapi.services

import com.bank.trainig.api.bankapi.datasource.DataSourceMock
import com.bank.trainig.api.bankapi.dtos.UpdateFeeDto
import com.bank.trainig.api.bankapi.dtos.UserDetailsDto
import com.bank.trainig.api.bankapi.dtos.UserDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val dataSource: DataSourceMock) {
    fun updateUser(user: UpdateFeeDto, userIdThatWillChangeFee: String) =
        dataSource.updateUserFee(user, userIdThatWillChangeFee)

    fun getUserById(userId: String): UserDto = dataSource.getUserById(userId)
    fun getUsers(): Collection<UserDto> = dataSource.getUsers()
    fun getUserDetailsById(userId: String): UserDetailsDto = dataSource.getUserDetailsById(userId)
}