package com.romantulchak.alphabet.model

import org.hibernate.Hibernate
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
data class Language(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Size(min = 2, max = 3)
    var code: String,
    @Size(min = 3, max = 35)
    var name: String,
    @OneToOne(mappedBy = "language", fetch = FetchType.LAZY)
    var alphabet: Alphabet?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Language

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , code = $code , name = $name , alphabet = $alphabet )"
    }
}
