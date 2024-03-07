package com.feature.resume.data.repository

import com.core.common.models.Resume
import com.core.common.models.ResumeWithTags
import com.core.network.ResumeApiService
import com.feature.resume.domain.repositories.ResumeRepository
import javax.inject.Inject

class ResumeRepositoryImpl @Inject constructor(private val resumeApiService: ResumeApiService) :
    ResumeRepository {
    override suspend fun findById(id: Long): ResumeWithTags? =
        resumeApiService.getResumeWithTags(id)


    override suspend fun update(resume: Resume) {
    }
}