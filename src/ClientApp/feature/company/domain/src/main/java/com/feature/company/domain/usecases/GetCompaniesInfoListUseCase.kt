package com.feature.company.domain.usecases

import com.feature.company.domain.models.CompanyDomainInfo
import com.feature.company.domain.repositories.CompanyRepository
import javax.inject.Inject

class GetCompaniesInfoListUseCase @Inject constructor(private val companyRepository: CompanyRepository) {
    suspend fun execute(): List<CompanyDomainInfo> = companyRepository.getCompaniesInfo()
}
