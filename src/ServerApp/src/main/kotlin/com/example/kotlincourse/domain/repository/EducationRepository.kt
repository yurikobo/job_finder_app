package com.example.kotlincourse.domain.repository

import com.example.kotlincourse.data.models.Education

interface EducationRepository : CrudRepository<Education> {
    suspend fun findAllByResumeId(id: Long): List<Education>?
}