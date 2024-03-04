package com.example.domain.repositories

import com.example.domain.models.VacancyDomain
import com.example.domain.models.VacancyDomainInfo

interface VacancyRepository {
    suspend fun getVacanciesInfo(): List<VacancyDomainInfo>

    suspend fun getVacancyDetails(id: Long): VacancyDomain?

}