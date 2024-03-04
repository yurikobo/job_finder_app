package com.example.domain.di

import com.example.domain.usecases.GetVacancyDetailsUseCase
import com.example.domain.usecases.GetVacancyInfoListUseCase
import dagger.Component

@Component(
//    modules = [CompanyDomainLayerModule::class],
    dependencies = [VacancyLayerComponentDependencies::class]
)
interface VacancyDomainLayerComponent {
    val getVacancyInfoListUseCase: GetVacancyInfoListUseCase
    val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
}