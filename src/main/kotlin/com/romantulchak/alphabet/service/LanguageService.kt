package com.romantulchak.alphabet.service

import com.romantulchak.alphabet.dto.LanguageDTO
import com.romantulchak.alphabet.request.CreateLanguageRequest

interface LanguageService {

    /**
     * Create language by property from client
     *
     * @param languageRequest to create language from properties
     */
    fun createLanguage(languageRequest: CreateLanguageRequest)


    /**
     * Finds all languages and sorts them by name
     *
     * @return list of sorted languages
     */
    fun findAllLanguages(): List<LanguageDTO>

}