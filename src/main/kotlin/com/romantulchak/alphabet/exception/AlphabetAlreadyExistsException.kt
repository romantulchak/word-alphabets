package com.romantulchak.alphabet.exception

class AlphabetAlreadyExistsException(code: String) : RuntimeException("Alphabet for language $code already exists")