package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyInfo(
    @SerialName("job_title") val jobTitle: Profession,
    @SerialName("candidate_level") val candidateLevel: CandidateLevel,
    @SerialName("salary_level") val salaryLevel: Int,
    @SerialName("company_name") val companyName: String
)