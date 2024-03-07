package com.feature.resume.domain.repositories

import com.core.common.models.Resume
import com.core.common.models.ResumeWithTags

interface ResumeRepository {
    suspend fun findById(id: Long): ResumeWithTags?
    suspend fun update(resume: Resume)
}