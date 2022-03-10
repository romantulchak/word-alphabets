package com.romantulchak.alphabet.exception

class LanguageNotFoundException(languageCode: String) : RuntimeException("Language with $languageCode not found")
