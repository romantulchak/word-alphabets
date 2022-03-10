package com.romantulchak.alphabet.controller

import com.romantulchak.alphabet.request.CreateAlphabetRequest
import com.romantulchak.alphabet.service.AlphabetService
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

@RestController
@CrossOrigin(value = ["*"], maxAge = 3600L)
@RequestMapping("/api/v1/alphabet")
class AlphabetController(private val alphabetService: AlphabetService) {

    @PostMapping("/create")
    fun create(@NotNull @RequestBody createAlphabetRequest: CreateAlphabetRequest){
        alphabetService.createAlphabetForLanguage(createAlphabetRequest)
    }

}