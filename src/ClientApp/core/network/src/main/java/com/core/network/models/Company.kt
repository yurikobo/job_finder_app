package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("field_of_activity") val fieldOfActivity: FieldOfActivity,
    @SerialName("vacancies") val listOfVacancies: List<Vacancy>,
    @SerialName("contacts") val contact: String
) {

}

