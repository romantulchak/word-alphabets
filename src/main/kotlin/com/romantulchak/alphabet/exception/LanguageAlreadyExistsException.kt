package com.romantulchak.alphabet.exception

class LanguageAlreadyExistsException(name: String) : RuntimeException("Language $name already exists")