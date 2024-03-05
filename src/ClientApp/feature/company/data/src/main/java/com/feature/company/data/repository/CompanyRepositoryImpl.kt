package com.feature.company.data.repository

import com.core.common.models.Company
import com.core.common.models.CompanyInfo
import com.core.network.CompanyApiService
import com.feature.company.data.mapper.toDomainCompany
import com.feature.company.data.mapper.toDomainCompanyInfoList
import com.feature.company.domain.repositories.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val retrofitService: CompanyApiService) :
    CompanyRepository {
    override suspend fun getCompaniesInfo(): List<CompanyInfo> =
        retrofitService.getCompaniesInfo().toDomainCompanyInfoList()

    override suspend fun getCompanyDetails(id: Long): Company? =
        retrofitService.getCompanyDetails(id)?.toDomainCompany()
}

