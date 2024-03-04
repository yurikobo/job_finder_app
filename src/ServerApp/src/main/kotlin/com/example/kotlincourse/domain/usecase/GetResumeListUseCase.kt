package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.domain.repository.ResumeRepository

class GetResumeListUseCase(private val resumeRepository: ResumeRepository) {

    fun execute(): List<Resume> = resumeRepository.findAll()
}