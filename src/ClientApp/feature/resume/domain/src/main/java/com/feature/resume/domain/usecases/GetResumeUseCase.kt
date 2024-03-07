package com.feature.resume.domain.usecases

import com.core.common.models.ResumeWithTags
import com.feature.resume.domain.repositories.ResumeRepository
import javax.inject.Inject

class GetResumeUseCase @Inject constructor(private val resumeRepository: ResumeRepository) {
    suspend fun execute(id: Long): ResumeWithTags? = resumeRepository.findById(id)
}