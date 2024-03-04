package com.feature.company.data.di

import com.core.network.CompanyApiService
import com.feature.company.data.repository.CompanyRepositoryImpl
import com.feature.company.domain.repositories.CompanyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CompanyDataLayerModule {

    @Provides
    @Singleton
    fun provideCompaniesRepository(companyApiService: CompanyApiService): CompanyRepository {
        return CompanyRepositoryImpl(companyApiService)
    }
}