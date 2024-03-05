package com.example.kotlincourse.di

import com.core.network.di.NetworkModule
import com.example.kotlincourse.MainActivity
import com.feature.company.data.di.CompanyDataLayerModule
import com.feature.company.domain.di.CompanyDomainLayerComponent
import com.feature.company.domain.di.CompanyLayerComponentDependencies
import com.feature.company.domain.repositories.CompanyRepository
import com.feature.company.ui.di.CompanyUiModule
import com.feature.vacancy.data.di.VacancyDataLayerModule
import com.feature.vacancy.domain.di.VacancyDomainLayerComponent
import com.feature.vacancy.domain.di.VacancyLayerComponentDependencies
import com.feature.vacancy.domain.repositories.VacancyRepository
import com.feature.vacancy.domain.usecases.GetVacancyInfoListUseCase
import com.feature.vacancy.ui.di.VacancyUiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, CompanyUiModule::class, VacancyUiModule::class, CompanyDataLayerModule::class, VacancyDataLayerModule::class],
    dependencies = [CompanyDomainLayerComponent::class, VacancyDomainLayerComponent::class]
)
interface AppContainer : CompanyLayerComponentDependencies, VacancyLayerComponentDependencies {

    fun inject(mainActivity: MainActivity)
    override fun getCompanyRepository(): CompanyRepository
    override fun getVacancyRepository(): VacancyRepository
    val getCompanyInfoListUseCase: GetVacancyInfoListUseCase
    val getVacancyInfoListUseCase: GetVacancyInfoListUseCase
}