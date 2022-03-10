package com.romantulchak.alphabet.model

import javax.persistence.Embeddable
import javax.validation.constraints.Size

@Embeddable
data class Letter(
    @Size(min = 1, max = 1)
    var letterLowerCase: Char,
    @Size(min = 1, max = 1)
    var letterUpperCase: Char,
    var asciiCode: Int
)