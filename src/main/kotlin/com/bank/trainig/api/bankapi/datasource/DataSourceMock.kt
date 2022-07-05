package com.bank.trainig.api.bankapi.datasource

import com.bank.trainig.api.bankapi.dtos.*
import com.bank.trainig.api.bankapi.model.AccountType
import com.bank.trainig.api.bankapi.model.User
import org.springframework.stereotype.Repository
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

@Repository
class DataSourceMock : DataSource {

    val users = mutableListOf(
        generateUser(AccountType.ADMIN, "mateuszszumny19@gmail.com", "123qwe", "Mateusz Szumny", 1000000)
    )


    override fun registerUser(user: RegisterUserDto): UserDto {
        if (users.any { it.login == user.login }) {
            throw IllegalArgumentException("User already exists.")
        }

        val newUser = User(
            generateGuid(),
            generateGuid(),
            user.accountType,
            user.login,
            user.password,
            0,
            generateTs(),
            user.userName
        )

        users.add(newUser)

        return UserDto(newUser.id, user.userName, user.accountType)
    }

    override fun login(login: LoginDto): UserDto {
        var user = users.first { it.login == login.login && it.password == login.password }

        return UserDto(user.id, user.userName, user.accountType)
    }

    override fun updateUserFee(updateFeeDto: UpdateFeeDto, userIdThatWillChangeFee: String) {

        val user = users.first { it.id == updateFeeDto.userId }

        val userThatWillChangeFee = users.first { it.id == userIdThatWillChangeFee }

        if (userThatWillChangeFee.accountType == AccountType.EXTERNALUSER
            && updateFeeDto.fee > user.fee
        )
            throw Exception("User ${user.userName} cannot increase fee, you don't have permission")

        users.remove(user)
        users.add(
            User(
                user.id,
                user.accountNumber,
                user.accountType,
                user.login,
                user.password,
                updateFeeDto.fee,
                generateTs(),
                user.userName
            )
        )
    }

    override fun getUserById(userId: String): UserDto {
        var user = users.find { it.id == userId } ?: throw IllegalArgumentException("User does not exist")

        return UserDto(user.id, user.userName, user.accountType)
    }

    override fun getUsers(): Collection<UserDto> = users.map { UserDto(it.id, it.userName, it.accountType) }.toList()

    override fun getUserDetailsById(userId: String): UserDetailsDto {
        var user = users.find { it.id == userId } ?: throw IllegalArgumentException("User does not exist")

        return UserDetailsDto(user.id, user.userName, user.accountType, user.fee)
    }

    private fun generateUser(
        accountType: AccountType,
        email: String,
        password: String,
        userName: String,
        fee: Int
    ): User {
        return User(
            generateGuid(),
            generateGuid(),
            accountType,
            email,
            password,
            fee,
            generateTs(),
            userName
        )
    }

    private fun generateGuid() = UUID.randomUUID().toString()

    private fun generateTs() = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
}