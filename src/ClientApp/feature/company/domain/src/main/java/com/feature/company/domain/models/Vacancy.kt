package com.feature.company.domain.models


data class Vacancy(
    val id: Long,
    val profession: Profession,
    val level: CandidateLevel,
    val salary: Int,
    val description: String
)

