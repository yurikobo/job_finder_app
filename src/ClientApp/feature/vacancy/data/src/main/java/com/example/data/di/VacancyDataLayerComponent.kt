package com.example.data.di

import com.core.network.di.NetworkModule
import com.example.domain.di.VacancyLayerComponentDependencies
import com.example.domain.repositories.VacancyRepository
import com.feature.vacancy.data.di.VacancyDataLayerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [VacancyDataLayerModule::class, NetworkModule::class]
)
interface VacancyDataLayerComponent : VacancyLayerComponentDependencies {

    override fun getVacancyRepository(): VacancyRepository

    val vacanceeeRepository: VacancyRepository


}