package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.domain.repository.ResumeRepository
import javax.inject.Inject

class GetResumeUseCase @Inject constructor(private val resumeRepository: ResumeRepository) {
    suspend fun execute(id: Long): Resume? = resumeRepository.findById(id)
}