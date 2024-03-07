package com.example.kotlincourse

import android.app.Application
import android.content.Context
import com.example.kotlincourse.di.AppContainer
import com.example.kotlincourse.di.DaggerAppContainer
import com.feature.company.data.di.DaggerCompanyDataLayerComponent
import com.feature.company.domain.di.CompanyDomainDependencyProvider
import com.feature.company.domain.di.CompanyLayerComponentDependencies
import com.feature.company.domain.di.DaggerCompanyDomainLayerComponent
import com.feature.resume.data.di.DaggerResumeDataLayerComponent
import com.feature.resume.domain.di.DaggerResumeDomainLayerComponent
import com.feature.resume.domain.di.ResumeDomainDependencyProvider
import com.feature.resume.domain.di.ResumeLayerComponentDependencies
import com.feature.vacancy.data.di.DaggerVacancyDataLayerComponent
import com.feature.vacancy.domain.di.DaggerVacancyDomainLayerComponent
import com.feature.vacancy.domain.di.VacancyDomainDependencyProvider
import com.feature.vacancy.domain.di.VacancyLayerComponentDependencies


class JobSearchingClientApplication : Application(), CompanyDomainDependencyProvider,
    VacancyDomainDependencyProvider, ResumeDomainDependencyProvider {

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
            .resumeDomainLayerComponent(
                DaggerResumeDomainLayerComponent.builder()
                    .resumeLayerComponentDependencies(DaggerResumeDataLayerComponent.create())
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

    override fun getResumeDomainDependencies(): ResumeLayerComponentDependencies {
        return appContainer
    }

}

val Context.appContainer: AppContainer
    get() = when (this) {
        is JobSearchingClientApplication -> appContainer
        else -> this.applicationContext.appContainer
    }