package com.example.kotlincourse.di

import com.core.network.di.NetworkModule
import com.example.kotlincourse.MainActivity
import com.feature.company.data.di.CompanyDataLayerModule
import com.feature.company.domain.di.CompanyDomainLayerComponent
import com.feature.company.domain.di.CompanyLayerComponentDependencies
import com.feature.company.domain.repositories.CompanyRepository
import com.feature.company.ui.di.CompanyUiModule
import com.feature.infoscreen.domain.usecases.GetCompaniesInfoListUseCase
import com.feature.infoscreen.domain.usecases.GetVacancyInfoListUseCase
import com.feature.infoscreen.ui.di.InfoScreenUiModule
import com.feature.resume.data.di.ResumeDataLayerModule
import com.feature.resume.domain.di.ResumeDomainLayerComponent
import com.feature.resume.domain.di.ResumeLayerComponentDependencies
import com.feature.resume.domain.repositories.ResumeRepository
import com.feature.resume.ui.di.ResumeUiModule
import com.feature.vacancy.data.di.VacancyDataLayerModule
import com.feature.vacancy.domain.di.VacancyDomainLayerComponent
import com.feature.vacancy.domain.di.VacancyLayerComponentDependencies
import com.feature.vacancy.domain.repositories.VacancyRepository
import com.feature.vacancy.ui.di.VacancyUiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, InfoScreenUiModule::class, CompanyUiModule::class, VacancyUiModule::class, ResumeUiModule::class, CompanyDataLayerModule::class, VacancyDataLayerModule::class, ResumeDataLayerModule::class],
    dependencies = [CompanyDomainLayerComponent::class, VacancyDomainLayerComponent::class, ResumeDomainLayerComponent::class]
)
interface AppContainer : CompanyLayerComponentDependencies, VacancyLayerComponentDependencies,
    ResumeLayerComponentDependencies {

    fun inject(mainActivity: MainActivity)
    override fun getCompanyRepository(): CompanyRepository
    override fun getVacancyRepository(): VacancyRepository
    override fun getResumeRepository(): ResumeRepository
    val getCompanyInfoListUseCase: GetCompaniesInfoListUseCase
    val getVacancyInfoListUseCase: GetVacancyInfoListUseCase
}