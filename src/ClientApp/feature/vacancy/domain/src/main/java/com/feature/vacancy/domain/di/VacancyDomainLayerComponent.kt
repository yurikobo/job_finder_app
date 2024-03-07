package com.feature.vacancy.domain.di

import com.feature.vacancy.domain.usecases.GetVacancyDetailsUseCase
import dagger.Component

@Component(
    dependencies = [VacancyLayerComponentDependencies::class]
)
interface VacancyDomainLayerComponent {
    val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
}