package com.bank.trainig.api.bankapi.services

import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(private val userService: UserService) {
    fun isAuthorized(request: HttpServletRequest): String {
        var header = request.getHeaders("Authorization")
        var tokens  = header.toList()
        var token: String? = tokens[0] ?: throw Exception("unauthorized")
        val body = Jwts.parser().setSigningKey("c2VjcmV0").parseClaimsJws(token).body

        val userId: String = body.get("userId").toString()

        var user = userService.getUserById(userId)

        return user.userId
    }
}