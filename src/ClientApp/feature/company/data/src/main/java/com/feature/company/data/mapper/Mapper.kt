package com.feature.company.data.mapper

import com.core.common.models.CandidateLevel
import com.core.common.models.Company
import com.core.common.models.CompanyInfo
import com.core.common.models.FieldOfActivity
import com.core.common.models.Profession
import com.core.common.models.Vacancy
import com.core.network.models.CompanyDTO
import com.core.network.models.CompanyInfoDTO
import com.core.network.models.VacancyDTO

fun List<CompanyInfoDTO>.toDomainCompanyInfoList(): List<CompanyInfo> {
    return this.map {
        CompanyInfo(
            it.id,
            it.name,
            FieldOfActivity.valueOf(it.fieldOfActivity.name)
        )
    }
}

fun List<VacancyDTO>.toDomainVacancyList(): List<Vacancy> {
    return this.map {
        Vacancy(
            it.id,
            Profession.valueOf(it.profession.name),
            CandidateLevel.valueOf(it.level.name),
            it.salary,
            it.description
        )
    }
}

fun CompanyDTO.toDomainCompany(): Company = Company(
    this.id,
    this.name,
    FieldOfActivity.valueOf(this.fieldOfActivity.name),
    listOfVacancies.toDomainVacancyList(),
    this.contact
)