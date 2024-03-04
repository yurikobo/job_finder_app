package com.feature.company.domain.models

data class CompanyDomain(
    val id: Long,
    val name: String,
    val fieldOfActivity: FieldOfActivity,
    val contact: String
)
