package com.romantulchak.alphabet.repository

import com.romantulchak.alphabet.model.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository : JpaRepository<Language, Long> {

    fun existsLanguageByCode(code: String): Boolean

    fun findByCode(code: String): Language?
}