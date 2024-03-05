package com.feature.company.ui.di

import com.feature.company.ui.navigation.CompanyApi
import com.feature.company.ui.navigation.CompanyApiImpl
import com.feature.company.ui.screen.CompanyViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CompanyUiModule {

    @Provides
    @Singleton
    fun provideCompanyApi(companyViewModel: CompanyViewModel): CompanyApi {
        return CompanyApiImpl(companyViewModel)
    }
}