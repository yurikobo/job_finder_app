package com.example.kotlincourse.data.models

import com.example.kotlincourse.data.models.CandidateInfo
import com.example.kotlincourse.data.models.Education
import com.example.kotlincourse.data.models.JobExperience
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Resume(
    @SerialName("id") val id: Long,
    @SerialName("candidate_info") val candidateInfo: CandidateInfo,
    @SerialName("education") val educationList: List<Education>,
    @SerialName("job_experience") val jobExperienceList: List<JobExperience>,
    @SerialName("free_form") val freeForm: String
)