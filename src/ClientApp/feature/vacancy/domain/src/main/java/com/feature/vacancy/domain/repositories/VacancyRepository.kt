package com.feature.vacancy.domain.repositories

import com.core.common.models.Vacancy
import com.core.common.models.VacancyInfo

interface VacancyRepository {
    suspend fun getVacanciesInfo(): List<VacancyInfo>

    suspend fun getVacancyDetails(id: Long): Vacancy?

}