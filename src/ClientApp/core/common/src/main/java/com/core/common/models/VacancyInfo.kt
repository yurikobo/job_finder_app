package com.core.common.models

data class VacancyInfo(
    val jobTitle: Profession,
    val candidateLevel: CandidateLevel,
    val salaryLevel: Int,
    val companyName: String
)