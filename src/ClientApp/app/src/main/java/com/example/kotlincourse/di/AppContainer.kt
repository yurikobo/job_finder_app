package com.example.kotlincourse.di

import com.core.network.di.NetworkModule
import com.example.domain.di.VacancyDomainLayerComponent
import com.example.domain.di.VacancyLayerComponentDependencies
import com.example.domain.repositories.VacancyRepository
import com.example.domain.usecases.GetVacancyDetailsUseCase
import com.example.domain.usecases.GetVacancyInfoListUseCase
import com.example.kotlincourse.MainActivity
import com.feature.company.data.di.CompanyDataLayerModule
import com.feature.company.domain.di.CompanyDomainLayerComponent
import com.feature.company.domain.di.CompanyLayerComponentDependencies
import com.feature.company.domain.repositories.CompanyRepository
import com.feature.company.domain.usecases.GetCompanyDetailsUseCase
import com.feature.vacancy.data.di.VacancyDataLayerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, CompanyDataLayerModule::class, VacancyDataLayerModule::class],
    dependencies = [CompanyDomainLayerComponent::class, VacancyDomainLayerComponent::class]
)
interface AppContainer : CompanyLayerComponentDependencies, VacancyLayerComponentDependencies {

    fun inject(mainActivity: MainActivity)
    override fun getCompanyRepository(): CompanyRepository
    override fun getVacancyRepository(): VacancyRepository
    val getCompanyInfoListUseCase: GetVacancyInfoListUseCase
    val getCompanyDetailsUseCase: GetCompanyDetailsUseCase
    val getVacancyInfoListUseCase: GetVacancyInfoListUseCase
    val getVacancyDetailsUseCase: GetVacancyDetailsUseCase
}