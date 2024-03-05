package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyInfoDTO(
    @SerialName("job_title") val jobTitle: ProfessionResponse,
    @SerialName("candidate_level") val candidateLevel: CandidateLevelResponse,
    @SerialName("salary_level") val salaryLevel: Int,
    @SerialName("company_name") val companyName: String
)