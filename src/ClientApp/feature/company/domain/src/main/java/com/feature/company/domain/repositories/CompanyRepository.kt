package com.feature.company.domain.repositories

import com.core.common.models.Company
import com.core.common.models.CompanyInfo

interface CompanyRepository {
    suspend fun getCompaniesInfo(): List<CompanyInfo>
    suspend fun getCompanyDetails(id: Long): Company?
}