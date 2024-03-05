package com.feature.vacancy.data.di

import com.core.network.di.NetworkModule
import com.feature.vacancy.domain.di.VacancyLayerComponentDependencies
import com.feature.vacancy.domain.repositories.VacancyRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [VacancyDataLayerModule::class, NetworkModule::class]
)
interface VacancyDataLayerComponent : VacancyLayerComponentDependencies {

    override fun getVacancyRepository(): VacancyRepository
    
}