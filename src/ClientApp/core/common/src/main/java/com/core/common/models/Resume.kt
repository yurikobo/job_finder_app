package com.core.common.models

data class Resume(
    val id: Long,
    val candidateInfo: CandidateInfo,
    val educationList: List<Education>,
    val jobExperienceList: List<JobExperience>,
    val freeForm: String
)