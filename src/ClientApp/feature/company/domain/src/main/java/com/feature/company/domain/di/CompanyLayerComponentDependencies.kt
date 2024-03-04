package com.feature.company.domain.di

import com.feature.company.domain.repositories.CompanyRepository

interface CompanyLayerComponentDependencies {
    fun getCompanyRepository(): CompanyRepository
}