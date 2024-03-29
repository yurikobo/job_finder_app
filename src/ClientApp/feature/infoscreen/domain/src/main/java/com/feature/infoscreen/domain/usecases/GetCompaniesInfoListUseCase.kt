package com.feature.infoscreen.domain.usecases

import com.feature.company.domain.repositories.CompanyRepository
import javax.inject.Inject

class GetCompaniesInfoListUseCase @Inject constructor(private val companyRepository: CompanyRepository) {
    suspend fun execute() = companyRepository.getCompaniesInfo()
}

