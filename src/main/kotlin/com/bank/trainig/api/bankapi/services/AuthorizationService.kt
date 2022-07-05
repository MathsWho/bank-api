package com.bank.trainig.api.bankapi.services

import com.bank.trainig.api.bankapi.datasource.DataSourceMock
import com.bank.trainig.api.bankapi.dtos.LoginDto
import com.bank.trainig.api.bankapi.dtos.RegisterUserDto
import org.springframework.stereotype.Service

@Service
class AuthorizationService(private val dataSource: DataSourceMock) {
    fun register(user: RegisterUserDto) = dataSource.registerUser(user)
    fun login(login:LoginDto) = dataSource.login(login)
}