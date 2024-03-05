package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyDTO(
    @SerialName("id") val id: Long,
    @SerialName("profession") val profession: ProfessionResponse,
    @SerialName("level") val level: CandidateLevelResponse,
    @SerialName("salary") val salary: Int,
    @SerialName("description") val description: String
)

