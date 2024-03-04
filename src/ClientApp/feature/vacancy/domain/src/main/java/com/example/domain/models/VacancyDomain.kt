package com.example.domain.models


data class VacancyDomain(
    val id: Long,
    val profession: Profession,
    val level: CandidateLevel,
    val salary: Int,
    val description: String
)

