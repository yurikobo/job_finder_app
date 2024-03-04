package com.example.domain.usecases

import com.example.domain.models.VacancyDomainInfo
import com.example.domain.repositories.VacancyRepository
import javax.inject.Inject

class GetVacancyInfoListUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend fun execute(): List<VacancyDomainInfo> = vacancyRepository.getVacanciesInfo()
}