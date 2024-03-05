package com.core.network.models

import com.core.network.adapters.LocalDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class CandidateInfo(
    @SerialName("name")
    val name: String,
    @SerialName("profession")
    val profession: ProfessionResponse,
    @SerialName("sex")
    val gender: Gender,
    @Serializable(LocalDateAdapter::class)
    @SerialName("birth_date")
    val birthDate: LocalDate,
    @SerialName("contacts")
    val contact: ContactResponse,
    @SerialName("relocation")
    val relocation: Relocation
)

@Serializable
enum class Relocation {
    @SerialName("preferable")
    PREFERABLE,

    @SerialName("possible")
    POSSIBLE,

    @SerialName("is not possible")
    IS_NOT_POSSIBLE;

    override fun toString(): String =
        name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " ")
}

@Serializable
enum class Gender {
    @SerialName("male")
    MALE,

    @SerialName("female")
    FEMALE;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}




