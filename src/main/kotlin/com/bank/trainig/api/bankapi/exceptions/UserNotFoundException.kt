package com.bank.trainig.api.bankapi.exceptions

class UserNotFoundException(message: String?) : Exception(message) {
    val statusCode: Int = 404
}