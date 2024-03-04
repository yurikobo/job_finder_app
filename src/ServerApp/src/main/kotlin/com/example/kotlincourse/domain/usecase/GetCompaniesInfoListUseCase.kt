package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.CompanyInfo
import com.example.kotlincourse.domain.repository.CompanyRepository

class GetCompaniesInfoListUseCase(private val companyRepository: CompanyRepository) {

    fun execute(): List<CompanyInfo> = companyRepository.findAll().map { CompanyInfo(it.id, it.name, it.fieldOfActivity) }
}