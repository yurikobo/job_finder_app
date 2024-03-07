package com.feature.company.domain.di

import com.feature.company.domain.usecases.GetCompanyDetailsUseCase
import dagger.Component

@Component(
    dependencies = [CompanyLayerComponentDependencies::class]
)
interface CompanyDomainLayerComponent {
    val getCompanyDetailsUseCase: GetCompanyDetailsUseCase
}