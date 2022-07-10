package com.bank.trainig.api.bankapi.controllers

import com.bank.trainig.api.bankapi.dtos.UpdateFeeDto
import com.bank.trainig.api.bankapi.model.AccountType
import com.bank.trainig.api.bankapi.services.AuthService
import com.bank.trainig.api.bankapi.services.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/api/users")
class UserController(private val userService: UserService, private val authService: AuthService) {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            authService.isAuthorized(request)

            var users = userService.getUsers()

            ResponseEntity.ok(users)
        } catch (ex: Exception) {
            ResponseEntity.status(400).body(ex.message)
        }
    }


    @PutMapping("/update-fee")
    @ResponseStatus(HttpStatus.OK)
    fun updateFee(@RequestBody updateFeeDto: UpdateFeeDto, request: HttpServletRequest): ResponseEntity<Any> {
        return try {

            var userId = authService.isAuthorized(request)

            userService.updateUser(updateFeeDto, userId)

            ResponseEntity.ok(userId)

        } catch (ex: Exception) {
            ResponseEntity.status(400).body(ex.message)
        }
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserDetails(@PathVariable userId: String, request: HttpServletRequest): ResponseEntity<Any> {
        return try {

            authService.isAuthorized(request)

            var users = userService.getUserDetailsById(userId)

            ResponseEntity.ok(users)

        } catch (ex: Exception) {
            ResponseEntity.status(400).body(ex.message)
        }
    }


}