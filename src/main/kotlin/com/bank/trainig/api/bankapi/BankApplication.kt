package com.bank.trainig.api.bankapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class BankApplication {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

}


fun main(args: Array<String>) {
    runApplication<BankApplication>(*args)
}

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping("/{id}")
    fun test(@PathVariable id: String) = id;
}
