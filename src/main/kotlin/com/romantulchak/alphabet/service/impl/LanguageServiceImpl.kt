package com.romantulchak.alphabet.service.impl

import com.romantulchak.alphabet.dto.LanguageDTO
import com.romantulchak.alphabet.exception.LanguageAlreadyExistsException
import com.romantulchak.alphabet.model.Language
import com.romantulchak.alphabet.repository.LanguageRepository
import com.romantulchak.alphabet.request.CreateLanguageRequest
import com.romantulchak.alphabet.service.LanguageService
import org.springframework.stereotype.Service

@Service
class LanguageServiceImpl(private val languageRepository: LanguageRepository) : LanguageService {

    /**
     * {@inheritDoc}
     */
    override fun createLanguage(languageRequest: CreateLanguageRequest) {
        if (languageRepository.existsLanguageByCode(languageRequest.code)) {
            throw LanguageAlreadyExistsException(languageRequest.name)
        }
        val language = Language(null, languageRequest.code, languageRequest.name, null)
        languageRepository.save(language)
    }

    /**
     * {@inheritDoc}
     */
    override fun findAllLanguages(): List<LanguageDTO> {
        return languageRepository.findAll()
            .map { LanguageDTO(it.id, it.code, it.name) }
            .sortedBy { it.name }
    }

}