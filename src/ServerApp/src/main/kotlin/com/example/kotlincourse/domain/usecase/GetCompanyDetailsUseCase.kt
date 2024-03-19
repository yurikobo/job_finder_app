package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompanyDetailsUseCase @Inject constructor(private val companyRepository: CompanyRepository) {

    suspend fun execute(id: Long) = companyRepository.findById(id)
}