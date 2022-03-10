package com.romantulchak.alphabet.service

import com.romantulchak.alphabet.request.CreateAlphabetRequest

interface AlphabetService {

    /**
     * Create alphabet for language
     *
     * @param createAlphabetRequest to create alphabet from properties for language
     */
    fun createAlphabetForLanguage(createAlphabetRequest: CreateAlphabetRequest)

}