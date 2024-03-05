package com.feature.company.data.di

import com.core.network.di.NetworkModule
import com.feature.company.domain.di.CompanyLayerComponentDependencies
import com.feature.company.domain.repositories.CompanyRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CompanyDataLayerModule::class, NetworkModule::class]
)
interface CompanyDataLayerComponent : CompanyLayerComponentDependencies {

    override fun getCompanyRepository(): CompanyRepository

}