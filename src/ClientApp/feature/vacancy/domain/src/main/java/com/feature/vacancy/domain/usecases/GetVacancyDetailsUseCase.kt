package com.feature.vacancy.domain.usecases

import com.core.common.models.Vacancy
import com.feature.vacancy.domain.repositories.VacancyRepository
import javax.inject.Inject

class GetVacancyDetailsUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend fun execute(id: Long): Vacancy? = vacancyRepository.getVacancyDetails(id)
}