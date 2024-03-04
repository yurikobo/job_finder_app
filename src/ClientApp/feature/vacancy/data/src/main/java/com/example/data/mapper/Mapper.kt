package com.example.data.mapper

import com.core.network.models.Vacancy
import com.core.network.models.VacancyInfo
import com.example.domain.models.CandidateLevel
import com.example.domain.models.Profession
import com.example.domain.models.VacancyDomain
import com.example.domain.models.VacancyDomainInfo


fun List<VacancyInfo>.toDomainVacancyInfoList(): List<VacancyDomainInfo> {
    return this.map {
        VacancyDomainInfo(
            Profession.valueOf(it.jobTitle.name),
            CandidateLevel.valueOf(it.candidateLevel.name),
            it.salaryLevel,
            it.companyName
        )
    }
}

fun Vacancy.toDomainVacancy(): VacancyDomain = VacancyDomain(
    this.id,
    Profession.valueOf(this.profession.name),
    CandidateLevel.valueOf(this.level.name),
    this.salary,
    this.description
)