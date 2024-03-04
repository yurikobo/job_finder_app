package com.feature.company.domain.repositories

import com.feature.company.domain.models.CompanyDomain
import com.feature.company.domain.models.CompanyDomainInfo

interface CompanyRepository {
    suspend fun getCompaniesInfo(): List<CompanyDomainInfo>
    suspend fun getCompanyDetails(id: Long): CompanyDomain?
}