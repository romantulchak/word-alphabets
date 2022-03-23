package com.romantulchak.alphabet.controller

import com.romantulchak.alphabet.dto.LanguageDTO
import com.romantulchak.alphabet.request.CreateLanguageRequest
import com.romantulchak.alphabet.service.LanguageService
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotNull

@RestController
@CrossOrigin(value = ["*"], maxAge = 3600L)
@RequestMapping("/api/v1/language")
class LanguageController(private val languageService: LanguageService) {

    @PostMapping("/create")
    fun createLanguage(@NotNull @RequestBody language: CreateLanguageRequest) {
        return languageService.createLanguage(language)
    }

    @GetMapping("/all")
    fun findAllLanguages(): List<LanguageDTO> {
        return languageService.findAllLanguages()
    }
}