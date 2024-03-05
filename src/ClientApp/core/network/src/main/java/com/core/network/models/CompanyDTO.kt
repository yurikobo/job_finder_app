package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyDTO(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("field_of_activity") val fieldOfActivity: FieldOfActivityResponse,
    @SerialName("vacancies") val listOfVacancies: List<VacancyDTO>,
    @SerialName("contacts") val contact: String
)
