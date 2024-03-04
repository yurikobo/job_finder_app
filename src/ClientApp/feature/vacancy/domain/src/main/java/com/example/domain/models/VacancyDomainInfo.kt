package com.example.domain.models


data class VacancyDomainInfo(
    val jobTitle: Profession,
    val candidateLevel: CandidateLevel,
    val salaryLevel: Int,
    val companyName: String
)