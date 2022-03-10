package com.romantulchak.alphabet.request

import javax.validation.constraints.Size

data class CreateLanguageRequest(
    @Size(min = 3, max = 35)
    val name: String,
    @Size(min = 2, max = 3)
    val code: String,
)
