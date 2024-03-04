package com.example.data.repositories

import com.core.network.VacancyApiService
import com.example.data.mapper.toDomainVacancy
import com.example.data.mapper.toDomainVacancyInfoList
import com.example.domain.models.VacancyDomain
import com.example.domain.models.VacancyDomainInfo
import com.example.domain.repositories.VacancyRepository
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(private val retrofitService: VacancyApiService) :
    VacancyRepository {
    override suspend fun getVacanciesInfo(): List<VacancyDomainInfo> =
        retrofitService.getVacanciesInfo().toDomainVacancyInfoList()

    override suspend fun getVacancyDetails(id: Long): VacancyDomain? =
        retrofitService.getVacancyDetails(id)?.toDomainVacancy()
}
