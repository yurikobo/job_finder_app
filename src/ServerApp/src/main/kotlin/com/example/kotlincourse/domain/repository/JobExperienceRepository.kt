package com.example.kotlincourse.domain.repository

import com.example.kotlincourse.data.models.JobExperience

interface JobExperienceRepository : CrudRepository<JobExperience> {
    suspend fun findAllByResumeId(id: Long): List<JobExperience>?
}