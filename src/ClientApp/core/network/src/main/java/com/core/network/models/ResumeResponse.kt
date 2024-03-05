package com.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeResponse(
    @SerialName("id") val id: Long,
    @SerialName("candidate_info") val candidateInfo: CandidateInfo,
    @SerialName("education") val educationList: List<EducationResponse>,
    @SerialName("job_experience") val jobExperienceList: List<JobExperienceResponse>,
    @SerialName("free_form") val freeForm: String
)