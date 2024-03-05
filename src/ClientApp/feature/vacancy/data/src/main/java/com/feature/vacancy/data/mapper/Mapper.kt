package com.feature.vacancy.data.mapper


import com.core.common.models.CandidateLevel
import com.core.common.models.Profession
import com.core.common.models.Vacancy
import com.core.common.models.VacancyInfo
import com.core.network.models.VacancyDTO
import com.core.network.models.VacancyInfoDTO


fun List<VacancyInfoDTO>.toDomainVacancyInfoList(): List<VacancyInfo> {
    return this.map {
        VacancyInfo(
            Profession.valueOf(it.jobTitle.name),
            CandidateLevel.valueOf(it.candidateLevel.name),
            it.salaryLevel,
            it.companyName
        )
    }
}

fun VacancyDTO.toDomainVacancy(): Vacancy = Vacancy(
    this.id,
    Profession.valueOf(this.profession.name),
    CandidateLevel.valueOf(this.level.name),
    this.salary,
    this.description
)