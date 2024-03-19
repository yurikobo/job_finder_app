package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.domain.repository.ResumeRepository
import javax.inject.Inject

class UpdateResumeUseCase @Inject constructor(private val resumeRepository: ResumeRepository) {
    suspend fun execute(resume: Resume): String =
        if (resumeRepository.findById(resume.id) != null) {
            resumeRepository.update(resume)
            "Resume with id(${resume.id}) was successfully updated"
        } else {
            "No resume with id(${resume.id}) was found"
        }

}