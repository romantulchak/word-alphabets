package com.romantulchak.alphabet.service

import com.romantulchak.alphabet.request.CreateLanguageRequest

interface LanguageService {

    /**
     * Create language by property from client
     *
     * @param languageRequest to create language from properties
     */
    fun createLanguage(languageRequest: CreateLanguageRequest)

}