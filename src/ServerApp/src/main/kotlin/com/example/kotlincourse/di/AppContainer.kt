package com.example.kotlincourse.di


import com.example.kotlincourse.domain.usecase.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface AppContainer {
    fun getCompaniesInfoListUseCase(): GetCompaniesInfoListUseCase
    fun getCompanyDetailsUseCase(): GetCompanyDetailsUseCase
    fun getVacanciesInfoListUseCase(): GetVacancyInfoListUseCase
    fun getVacancyDetailsUseCase(): GetVacancyDetailsUseCase
    fun getResumeListUseCase(): GetResumeListUseCase
    fun getResumeUseCase(): GetResumeUseCase
    fun getUpdateResumeUseCase(): UpdateResumeUseCase
}