package com.feature.company.data.mapper

import com.core.network.models.Company
import com.core.network.models.CompanyInfo
import com.feature.company.domain.models.CompanyDomain
import com.feature.company.domain.models.CompanyDomainInfo
import com.feature.company.domain.models.FieldOfActivity

fun List<CompanyInfo>.toDomainCompanyInfoList(): List<CompanyDomainInfo> {
    return this.map {
        CompanyDomainInfo(
            it.id,
            it.name,
            FieldOfActivity.valueOf(it.fieldOfActivity.name)
        )
    }
}

fun Company.toDomainCompany(): CompanyDomain = CompanyDomain(
    this.id,
    this.name,
    FieldOfActivity.valueOf(this.fieldOfActivity.name),
    this.contact
)