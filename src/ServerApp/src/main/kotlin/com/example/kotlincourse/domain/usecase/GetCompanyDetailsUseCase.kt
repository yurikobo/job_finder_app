package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.domain.repository.CompanyRepository

class GetCompanyDetailsUseCase(private val companyRepository: CompanyRepository) {

    fun execute(id: Long) = companyRepository.findById(id)
}