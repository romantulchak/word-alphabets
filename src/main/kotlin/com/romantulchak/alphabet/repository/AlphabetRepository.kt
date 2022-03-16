package com.romantulchak.alphabet.repository

import com.romantulchak.alphabet.model.Alphabet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlphabetRepository : JpaRepository<Alphabet, Long> {

    fun findAllByLanguageCode(code: String): Alphabet

    fun existsByCode(code: String): Boolean
}