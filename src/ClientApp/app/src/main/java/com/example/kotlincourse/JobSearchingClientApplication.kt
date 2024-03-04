package com.example.kotlincourse

import android.app.Application
import android.content.Context
import com.example.data.di.DaggerVacancyDataLayerComponent
import com.example.domain.di.DaggerVacancyDomainLayerComponent
import com.example.domain.di.VacancyDomainDependencyProvider
import com.example.domain.di.VacancyLayerComponentDependencies
import com.example.kotlincourse.di.AppContainer
import com.example.kotlincourse.di.DaggerAppContainer
import com.feature.company.data.di.DaggerCompanyDataLayerComponent
import com.feature.company.domain.di.CompanyDomainDependencyProvider
import com.feature.company.domain.di.CompanyLayerComponentDependencies
import com.feature.company.domain.di.DaggerCompanyDomainLayerComponent


class JobSearchingClientApplication : Application(), CompanyDomainDependencyProvider,
    VacancyDomainDependencyProvider {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = DaggerAppContainer.builder()
            .companyDomainLayerComponent(
                DaggerCompanyDomainLayerComponent.builder()
                    .companyLayerComponentDependencies(DaggerCompanyDataLayerComponent.create())
                    .build()
            )
            .vacancyDomainLayerComponent(
                DaggerVacancyDomainLayerComponent.builder()
                    .vacancyLayerComponentDependencies(DaggerVacancyDataLayerComponent.create())
                    .build()
            )
            .build()
    }

    override fun getCompanyDomainDependencies(): CompanyLayerComponentDependencies {
        return appContainer
    }

    override fun getVacancyDomainDependencies(): VacancyLayerComponentDependencies {
        return appContainer
    }

}

val Context.appContainer: AppContainer
    get() = when (this) {
        is JobSearchingClientApplication -> appContainer
        else -> this.applicationContext.appContainer
    }