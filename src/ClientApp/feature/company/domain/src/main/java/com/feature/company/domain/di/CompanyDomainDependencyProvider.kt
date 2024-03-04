package com.feature.company.domain.di

interface CompanyDomainDependencyProvider {
    fun getCompanyDomainDependencies(): CompanyLayerComponentDependencies
}