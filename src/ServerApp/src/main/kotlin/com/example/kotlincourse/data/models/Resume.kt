package com.example.kotlincourse.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Resume(
    @SerialName("id") val id: Long,
    @SerialName("candidate_info") val candidateInfo: CandidateInfo?,
    @SerialName("education") val educationList: List<Education>?,
    @SerialName("job_experience") val jobExperienceList: List<JobExperience>?,
    @SerialName("free_form") val freeForm: String?
)

object Resumes : Table() {
    val id = long("id").autoIncrement()
    val freeForm = varchar("free_form", 255)

    override val primaryKey = PrimaryKey(id)
}