package com.romantulchak.alphabet.service

import com.romantulchak.alphabet.dto.AlphabetDTO
import com.romantulchak.alphabet.request.CreateAlphabetRequest
import org.springframework.web.multipart.MultipartFile

interface AlphabetService {

    /**
     * Create alphabet for language
     *
     * @param createAlphabetRequests to create alphabet from properties for language
     */
    fun createAlphabetForLanguage(createAlphabetRequests: List<CreateAlphabetRequest>)

    /**
     * Gets alphabet for language
     *
     * @param languageCode to find language by its code
     * @return alphabet for language
     */
    fun getAlphabetForLanguage(languageCode: String): AlphabetDTO

    /**
     * Create alphabet from file
     *
     * @param file to required attributes to create alphabet
     */
    fun createAlphabetFromFile(file: MultipartFile)
}