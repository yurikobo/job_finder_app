package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    @SerialName("id") val id: Long,
    @SerialName("profession") val profession: Profession,
    @SerialName("level") val level: CandidateLevel,
    @SerialName("salary") val salary: Int,
    @SerialName("description") val description: String
)

