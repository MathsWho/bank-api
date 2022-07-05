package com.bank.trainig.api.bankapi.controllers

import com.bank.trainig.api.bankapi.dtos.UpdateFeeDto
import com.bank.trainig.api.bankapi.model.AccountType
import com.bank.trainig.api.bankapi.services.AuthService
import com.bank.trainig.api.bankapi.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService, private val authService: AuthService) {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null)
                return ResponseEntity.status(401).body("unauthenticated")

            authService.isAuthorized(jwt)

            var users = userService.getUsers()

            return ResponseEntity.ok(users)

        } catch (ex: Exception) {
            return ResponseEntity.status(400).body(ex.message)
        }
    }


    @PostMapping("/update-fee")
    @ResponseStatus(HttpStatus.OK)
    fun updateFee(@RequestBody updateFeeDto: UpdateFeeDto, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null)
                return ResponseEntity.status(401).body("unauthenticated")

            var userId = authService.isAuthorized(jwt)

            userService.updateUser(updateFeeDto, userId)

            return ResponseEntity.ok(userId)

        } catch (ex: Exception) {
            return ResponseEntity.status(400).body(ex.message)
        }
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserDetails(@PathVariable userId: String, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null)
                return ResponseEntity.status(401).body("unauthenticated")

            authService.isAuthorized(jwt)

            var users = userService.getUserDetailsById(userId)

            return ResponseEntity.ok(users)

        } catch (ex: Exception) {
            return ResponseEntity.status(400).body(ex.message)
        }
    }


}