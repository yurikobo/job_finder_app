package com.example.domain.usecases

import com.example.domain.models.VacancyDomain
import com.example.domain.repositories.VacancyRepository
import javax.inject.Inject

class GetVacancyDetailsUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend fun execute(id: Long): VacancyDomain? = vacancyRepository.getVacancyDetails(id)
}