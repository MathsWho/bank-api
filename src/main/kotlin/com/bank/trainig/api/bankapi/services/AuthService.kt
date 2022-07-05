package com.bank.trainig.api.bankapi.services

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class AuthService(private val userService: UserService) {
    fun isAuthorized(jwt: String?): String {
        val body = Jwts.parser().setSigningKey("c2VjcmV0").parseClaimsJws(jwt).body

        val userId = body.issuer;

        var user = userService.getUserById(userId)

        return user.userId
    }
}