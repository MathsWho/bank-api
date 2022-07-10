package com.bank.trainig.api.bankapi.controllers

import com.bank.trainig.api.bankapi.dtos.LoginDto
import com.bank.trainig.api.bankapi.dtos.RegisterUserDto
import com.bank.trainig.api.bankapi.dtos.UserDto
import com.bank.trainig.api.bankapi.exceptions.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.bank.trainig.api.bankapi.services.AuthorizationService
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/api/authorization")
class AuthorizationController(private val registerService: AuthorizationService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody user: RegisterUserDto): UserDto = registerService.register(user)

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody login: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
        try {
            var user = registerService.login(login)
            val issuer = user.userId

            var claims = Jwts.claims()
            claims.put("AccountType", user.accountType)
            claims.put("userId",  user.userId)

            var jwt = Jwts.builder()
                .setIssuer(issuer)
                .setClaims(claims)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
                .signWith(SignatureAlgorithm.HS512, "c2VjcmV0").compact()

            val cookie = Cookie("jwt", jwt)
            cookie.isHttpOnly = true

            response.addCookie(cookie)

            return ResponseEntity.ok(jwt)

        } catch (ex: UserNotFoundException) {
            return ResponseEntity.status(ex.statusCode).body(ex.message)
        }
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    fun logout(response: HttpServletResponse) {

        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)
    }
}