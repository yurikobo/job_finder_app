package com.feature.company.domain.usecases

import com.feature.company.domain.models.CompanyDomain
import com.feature.company.domain.repositories.CompanyRepository
import javax.inject.Inject

class GetCompanyDetailsUseCase @Inject constructor(private val companyRepository: CompanyRepository) {
    suspend fun execute(id: Long): CompanyDomain? = companyRepository.getCompanyDetails(id)
}
