package com.feature.resume.domain.usecases

import com.core.common.models.Resume
import com.feature.resume.domain.repositories.ResumeRepository
import javax.inject.Inject

class UpdateResumeUseCase @Inject constructor(private val resumeRepository: ResumeRepository) {
    suspend fun execute(resume: Resume) = resumeRepository.update(resume)
}