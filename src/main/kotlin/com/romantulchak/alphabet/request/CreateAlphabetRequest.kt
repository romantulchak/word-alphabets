package com.romantulchak.alphabet.request

data class CreateAlphabetRequest(
    var letters: List<Char>,
    var languageCode: String
)