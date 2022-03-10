package com.romantulchak.alphabet.service.impl

import com.romantulchak.alphabet.exception.LanguageNotFoundException
import com.romantulchak.alphabet.model.Alphabet
import com.romantulchak.alphabet.model.Letter
import com.romantulchak.alphabet.repository.AlphabetRepository
import com.romantulchak.alphabet.repository.LanguageRepository
import com.romantulchak.alphabet.request.CreateAlphabetRequest
import com.romantulchak.alphabet.service.AlphabetService
import org.springframework.stereotype.Service

@Service
class AlphabetServiceImpl(
    private val alphabetRepository: AlphabetRepository,
    private val languageRepository: LanguageRepository
) : AlphabetService {

    /**
     * {@inheritDoc}
     */
    override fun createAlphabetForLanguage(createAlphabetRequest: CreateAlphabetRequest) {
        val language = languageRepository.findByCode(createAlphabetRequest.languageCode)
        if (language === null) {
            throw LanguageNotFoundException(createAlphabetRequest.languageCode)
        }
        val letters: MutableList<Letter> = arrayListOf()
        createAlphabetRequest.letters.forEach {
            val letter = Letter(
                it.lowercaseChar(),
                it.uppercaseChar(),
                it.code
            )
            letters.add(letter)
        }
        val alphabet = Alphabet(null, letters, language, language.code)
        alphabetRepository.save(alphabet)
    }
}