package com.core.common.models

import java.time.LocalDate

data class CandidateInfo(
    val name: String,
    val profession: Profession,
    val gender: Gender,
    val birthDate: LocalDate,
    val contact: Contact,
    val relocation: Relocation
)

enum class Relocation {
    PREFERABLE,

    POSSIBLE,

    IS_NOT_POSSIBLE;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}

enum class Gender {
    MALE,

    FEMALE;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}




