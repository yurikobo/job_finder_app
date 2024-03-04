package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.domain.repository.ResumeRepository

class GetResumeUseCase(private val resumeRepository: ResumeRepository) {
    fun execute(id: Long): Resume? = resumeRepository.findById(id)
}