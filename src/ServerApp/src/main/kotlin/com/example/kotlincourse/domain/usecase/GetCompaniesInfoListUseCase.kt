package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.CompanyInfo
import com.example.kotlincourse.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompaniesInfoListUseCase @Inject constructor(private val companyRepository: CompanyRepository) {

    suspend fun execute(): List<CompanyInfo> =
        companyRepository.findAll().map { CompanyInfo(it.id, it.name, it.fieldOfActivity) }
}