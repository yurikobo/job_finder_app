package com.feature.vacancy.data.repositories

import com.core.common.models.Vacancy
import com.core.common.models.VacancyInfo
import com.core.network.VacancyApiService
import com.feature.vacancy.data.mapper.toDomainVacancy
import com.feature.vacancy.data.mapper.toDomainVacancyInfoList
import com.feature.vacancy.domain.repositories.VacancyRepository
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(private val retrofitService: VacancyApiService) :
    VacancyRepository {
    override suspend fun getVacanciesInfo(): List<VacancyInfo> =
        retrofitService.getVacanciesInfo().toDomainVacancyInfoList()

    override suspend fun getVacancyDetails(id: Long): Vacancy? =
        retrofitService.getVacancyDetails(id)?.toDomainVacancy()
}
