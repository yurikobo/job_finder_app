package com.feature.vacancy.domain.usecases

import com.core.common.models.VacancyInfo
import com.feature.vacancy.domain.repositories.VacancyRepository
import javax.inject.Inject

class GetVacancyInfoListUseCase @Inject constructor(private val vacancyRepository: VacancyRepository) {
    suspend fun execute(): List<VacancyInfo> = vacancyRepository.getVacanciesInfo()
}