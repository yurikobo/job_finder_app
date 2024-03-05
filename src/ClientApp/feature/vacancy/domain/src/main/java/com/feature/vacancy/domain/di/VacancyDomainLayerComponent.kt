package com.feature.vacancy.domain.di

import com.feature.vacancy.domain.usecases.GetVacancyDetailsUseCase
import com.feature.vacancy.domain.usecases.GetVacancyInfoListUseCase
import dagger.Component

@Component(
    dependencies = [VacancyLayerComponentDependencies::class]
)
interface VacancyDomainLayerComponent {
    val getVacancyInfoListUseCase: GetVacancyInfoListUseCase
    val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
}