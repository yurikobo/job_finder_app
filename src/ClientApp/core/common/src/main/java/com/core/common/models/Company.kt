package com.core.common.models


data class Company(
    val id: Long,
    val name: String,
    val fieldOfActivity: FieldOfActivity,
    val listOfVacancies: List<Vacancy>,
    val contact: String
)

