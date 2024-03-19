package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.VacancyInfo
import com.example.kotlincourse.domain.repository.CompanyRepository
import javax.inject.Inject

class GetVacancyInfoListUseCase @Inject constructor(private val companyRepository: CompanyRepository) {
    suspend fun execute(): List<VacancyInfo> {
        return companyRepository.findAll()
            .flatMap { company ->
                company.listOfVacancies.map { vacancy ->
                    VacancyInfo(
                        jobTitle = vacancy.profession,
                        candidateLevel = vacancy.level,
                        salaryLevel = vacancy.salary,
                        companyName = company.name
                    )
                }
            }
    }

}