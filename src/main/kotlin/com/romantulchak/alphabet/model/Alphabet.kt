package com.romantulchak.alphabet.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Alphabet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @ElementCollection
    var letters: List<Letter>,
    @OneToOne
    var language: Language,
    var code: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Alphabet

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , alphabetLetters = $letters , language = $language)"
    }

}
