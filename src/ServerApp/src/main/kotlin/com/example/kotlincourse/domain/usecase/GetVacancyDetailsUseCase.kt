package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.Vacancy
import com.example.kotlincourse.domain.repository.VacancyRepository

class GetVacancyDetailsUseCase(private val vacancyRepository: VacancyRepository) {
    fun execute(id: Long): Vacancy? = vacancyRepository.findById(id)
}