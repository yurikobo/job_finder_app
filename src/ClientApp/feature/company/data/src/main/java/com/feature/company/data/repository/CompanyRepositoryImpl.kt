package com.feature.company.data.repository

import com.core.common.models.Company
import com.core.common.models.CompanyInfo
import com.core.network.CompanyApiService
import com.feature.company.domain.repositories.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val retrofitService: CompanyApiService) :
    CompanyRepository {
    override suspend fun getCompaniesInfo(): List<CompanyInfo> = retrofitService.getCompaniesInfo()

    override suspend fun getCompanyDetails(id: Long): Company? =
        retrofitService.getCompanyDetails(id)
}

