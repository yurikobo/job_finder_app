package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Vacancy
import com.example.kotlincourse.domain.repository.VacancyRepository
import javax.inject.Inject

class GetVacancyDetailsUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend fun execute(id: Long): Vacancy? = vacancyRepository.findById(id)
}