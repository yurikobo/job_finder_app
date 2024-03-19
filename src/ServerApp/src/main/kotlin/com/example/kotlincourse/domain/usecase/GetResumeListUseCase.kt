package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.domain.repository.ResumeRepository
import javax.inject.Inject

class GetResumeListUseCase @Inject constructor(private val resumeRepository: ResumeRepository) {

    suspend fun execute(): List<Resume> = resumeRepository.findAll()
}